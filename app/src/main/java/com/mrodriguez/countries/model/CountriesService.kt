package com.mrodriguez.countries.model

import android.util.Log
import com.mrodriguez.countries.Country
import com.mrodriguez.countries.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class CountriesService {
    @Inject
    lateinit var api: CountriesApi
    @Inject
    lateinit var name: String


    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCountries(): Single<List<Country>>{
        Log.d("maickol",name)
        return api.getCountries();
    }
}