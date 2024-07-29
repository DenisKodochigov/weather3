package com.example.weather3.ui.screens.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import com.example.weather3.entity.LocApp
import com.example.weather3.entity.Weathers
import javax.inject.Singleton

@Singleton
data class MainScreenState (
    val location: MutableState<LocApp> = mutableStateOf(LocApp()),
    val weathers: Weathers? = null,

    @Stable var getWeather: () -> Unit = {},
    @Stable var onEnterCity: (String) -> Unit = {},
)