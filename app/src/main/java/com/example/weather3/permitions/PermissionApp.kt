package com.example.weather3.permitions

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.ACCESS_NETWORK_STATE
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Settings
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.weather3.R
//import com.example.weather3.ui.theme.dialogButtonStyle
//import com.example.weather3.ui.theme.dialogButtonStyleLight
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.shouldShowRationale

@Composable
fun RequestPermissionsAll(context: Context): Boolean = RequestPermissions(context, permissions1)

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestPermissions(context: Context,permissions: List<String>): Boolean{
    val openAlertDialog = remember { mutableStateOf(false) }
    val permissionsState = rememberMultiplePermissionsState(permissions = permissions)
    var handleSetPermission = ""
    if(openAlertDialog.value) {
        DialogRequestPermission(
            onCancel = { openAlertDialog.value = false },
            onOk = {
                openAlertDialog.value = false
                onConfirmation(context, handleSetPermission)
            },
        )
    }

    permissionsState.permissions.forEach { permissionState->
        if (permissionState.status != PermissionStatus.Granted){
            if (!permissionState.status.shouldShowRationale){
                LaunchedEffect(key1 = Unit ){ permissionState.launchPermissionRequest() }
            } else{
                LaunchedEffect(key1 = Unit ){
                    handleSetPermission = permissionState.permission
                    openAlertDialog.value = true }
            }
        }
    }

    return !permissionsState.allPermissionsGranted
}

@Composable fun DialogRequestPermission(onOk:()->Unit, onCancel:()->Unit){
    AlertDialog(
        title = { Text(text = stringResource(id = R.string.title_request_permission)) },
        text = { Text(text = stringResource(id = R.string.text_request_permission)) },
        onDismissRequest = onCancel,
        confirmButton = { Text(text = "OK", style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(horizontal = 24.dp).clickable { onOk() }) },
        dismissButton = { Text(text = "Cancel", style =  MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(horizontal = 24.dp).clickable { onCancel() })  },
    )
}
fun onConfirmation(context: Context, handleSetPermission: String){
    val intent = Intent()
    intent.action = actionPermission(handleSetPermission)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    context.startActivity( intent)
}

fun actionPermission(permission: String): String{
    return when (permission){
        ACCESS_COARSE_LOCATION -> Settings.ACTION_LOCATION_SOURCE_SETTINGS
        ACCESS_FINE_LOCATION -> Settings.ACTION_LOCATION_SOURCE_SETTINGS
        else -> Settings.ACTION_SETTINGS
    }
}

fun checkPermission(context: Context): Boolean{
    return (ActivityCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
}
val permissions1 = listOf(
    ACCESS_NETWORK_STATE,
    ACCESS_FINE_LOCATION,
    ACCESS_COARSE_LOCATION,
)