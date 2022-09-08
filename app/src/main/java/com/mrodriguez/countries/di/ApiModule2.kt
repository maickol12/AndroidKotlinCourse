package com.mrodriguez.countries.di

import com.mrodriguez.countries.model.CountriesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule2 {
    private val BASE_URL = "https://raw.githubusercontent.com/"
    @Provides
    fun provideName(): String{
        return "MAICKOL "
    }



}