package com.example.weather3.ui.screens.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather3.data.DataRepository
import com.example.weather3.domain.lg
import com.example.weather3.entity.ErrorApp
import com.example.weather3.entity.LocApp
import com.example.weather3.service_location.Internet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val errorApp: ErrorApp,
    private val internet: Internet,
    private val dataRepository: DataRepository
): ViewModel(){
    private val _mainScreenState = MutableStateFlow(MainScreenState(
        onEnterCity = {city -> onEnterCity(city)},
    ))
    val mainScreenState: StateFlow<MainScreenState> = _mainScreenState.asStateFlow()

    fun availableInternet() = internet.isOnline()

    fun getLocation(){
        if (internet.isOnline()) {
            viewModelScope.launch(Dispatchers.IO) {
                kotlin.runCatching { dataRepository.getLocation() }.fold(
                    onSuccess = {
                        it?.let {
                            getWeather(it)
                            _mainScreenState.update { currentState ->
                                currentState.copy(location = mutableStateOf(it))
                            }
                        }
                    },
                    onFailure = { errorApp.errorApi(it.message!!) }
                )
            }
        }
    }
    private fun getWeather(locApp: LocApp){
        if (internet.isOnline()) {
            viewModelScope.launch(Dispatchers.IO) {
                kotlin.runCatching { dataRepository.getWeathers(locApp) }.fold(
                    onSuccess = {
                        _mainScreenState.update { currentState -> currentState.copy( weathers = it ) } },
                    onFailure = { errorApp.errorApi(it.message!!) }
                )
            }
        }
    }
    private fun onEnterCity(city: String){
        if (internet.isOnline()) {
            viewModelScope.launch(Dispatchers.IO) {
                kotlin.runCatching { dataRepository.getLocationFromAddress(city) }.fold(
                    onSuccess = {
                        it?.let {
                            getWeather(it)
                            _mainScreenState.update { currentState ->
                                currentState.copy(location = mutableStateOf(it))
                            }
                        }
                    },
                    onFailure = { errorApp.errorApi(it.message!!) }
                )
            }
        }
    }
}