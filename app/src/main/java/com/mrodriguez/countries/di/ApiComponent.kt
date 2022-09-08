package com.mrodriguez.countries.di

import com.mrodriguez.countries.model.CountriesService
import com.mrodriguez.countries.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class,ApiModule2::class])
interface ApiComponent {
    fun inject(service: CountriesService)
    fun inject(viewModel: ListViewModel)
}