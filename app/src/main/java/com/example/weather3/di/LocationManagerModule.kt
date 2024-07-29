package com.example.weather3.di

import android.content.Context
import com.example.weather3.service_location.LocationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocationManagerModule {
    @Provides
    @Singleton
    fun provideLocationManager(@ApplicationContext appContext: Context): LocationManager {
        return LocationManager(appContext)
    }
}