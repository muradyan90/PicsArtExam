package com.aram.picsartexam

import com.aram.picsartexam.network.ApiService
import com.aram.picsartexam.repository.Repository
import com.aram.picsartexam.viewmodel.ItemsViewModel
import com.aram.picsartexam.viewmodel.LoginViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_URL = "https://api.picsart.com"

val koinModule = module {
    single { Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build() }

    single { Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(get()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(ApiService::class.java)}

    single { Repository(get()) }

    viewModel { ItemsViewModel(get()) }
    viewModel { LoginViewModel(get()) }
}