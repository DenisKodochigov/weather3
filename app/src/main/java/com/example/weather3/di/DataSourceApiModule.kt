package com.example.weather3.di

import com.example.weather3.data.openmeteo_api.DataSourceAPI
import com.example.weather3.data.openmeteo_api.OpenMeteoAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceApiModule {
    @Provides
    @Singleton
    fun provideDataSourceApi(openMeteo: OpenMeteoAPI) = DataSourceAPI(openMeteo)
}