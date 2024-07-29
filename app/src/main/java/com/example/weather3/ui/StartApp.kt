package com.example.weather3.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weather3.ui.screens.main.MainScreen
import com.example.weather3.ui.theme.ThemeApp

@Composable
fun StartApp() {
    ThemeApp {
        Scaffold(
            modifier = Modifier,
            content = { innerPadding -> MainScreen(modifier = Modifier.padding(innerPadding)) }
        )
    }
}


@Preview
@Composable
fun StartAppPreview(){
    StartApp()
}