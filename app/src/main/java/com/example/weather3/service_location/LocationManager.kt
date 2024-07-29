package com.example.weather3.service_location

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import com.example.weather3.domain.lg
import com.example.weather3.entity.LocApp
import com.example.weather3.permitions.checkPermission
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority.PRIORITY_LOW_POWER
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocationManager@Inject constructor(val context: Context) {
    private val cancellationSource = CancellationTokenSource()
    private val token = cancellationSource.token
//    private val token = object : CancellationToken() {
//        override fun onCanceledRequested(p0: OnTokenCanceledListener) = cancellationSource.token
//        override fun isCancellationRequested() = false
//    }
    private val fusedClient = LocationServices.getFusedLocationProviderClient(context)

    suspend fun getLocation(): LocApp? {
        return if (checkPermission(context)) {
            suspendCoroutine { continuation ->
                fusedClient.getCurrentLocation(PRIORITY_LOW_POWER, token)
                .addOnSuccessListener { loc ->
                    lg("getLocation $loc")
                    if (loc != null) {
                        if (loc.latitude != 0.0 && loc.longitude != 0.0){
                            continuation.resume(
                                LocApp(
                                    city = getAddressFromLocation(loc),
                                    latitude = loc.latitude,
                                    longitude = loc.longitude
                                )
                            )
                        }
                    }
                }
            }
        } else { null }
    }
    suspend fun getLocation(address: String): LocApp? {
        return  suspendCoroutine { continuation ->
                continuation.resume(getLocationFromAddress(address))
            }
    }
    private fun getAddressFromLocation(location: Location): String{
        val geocoder = Geocoder(context)
        var list: MutableList<Address>? = mutableListOf()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            geocoder.getFromLocation(location.latitude, location.longitude, 1
            ) { addresses -> list = addresses }
        } else {
            list = geocoder.getFromLocation(location.latitude, location.longitude, 1)
        }
        val address = if (list.isNullOrEmpty()) "" else {
            list!![0].locality + ", " + list!![0].countryName
        }
        return address
    }

    private fun getLocationFromAddress(address: String): LocApp?{
        var locApp: LocApp? = null
        val geocoder = Geocoder(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            geocoder.getFromLocationName(address, 1
            ) { addresses ->
                if (addresses.size > 0) {
                    locApp = LocApp(
                        city = addresses[0].adminArea + ", " + addresses[0].countryName,
                        latitude = addresses[0].latitude,
                        longitude = addresses[0].longitude
                    )
                }
            }
        } else {
            try {
                var addresses = geocoder.getFromLocationName(address, 1)
                while (addresses!!.size == 0) { addresses = geocoder.getFromLocationName(address, 1) }
                if (addresses.size > 0) {
                    locApp = LocApp(
                        city = addresses[0].locality + ", " + addresses[0].countryName,
                        latitude = addresses[0].latitude,
                        longitude = addresses[0].longitude
                    )
                }
            } catch (e: Exception) {
                print(e.message)
            }
        }
        return locApp
    }
    fun cancel(){
        cancellationSource.cancel()
        if(checkPermission(context)) fusedClient.lastLocation.isCanceled
    }
}