package com.example.weather3.di

import android.content.Context
import com.example.weather3.service_location.Internet
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InternetModule {
    @Provides
    @Singleton
    fun provideInternet(@ApplicationContext appContext: Context): Internet {
        return Internet(appContext)
    }
}