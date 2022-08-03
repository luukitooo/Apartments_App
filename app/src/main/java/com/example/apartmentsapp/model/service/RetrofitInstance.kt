package com.example.apartmentsapp.model.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://run.mocky.io/"

    private val instance by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Synchronized
    fun getApartmentApi(): ApartmentService {
        return instance.create(ApartmentService::class.java)
    }

}