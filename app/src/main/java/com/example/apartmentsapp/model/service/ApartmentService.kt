package com.example.apartmentsapp.model.service

import com.example.apartmentsapp.model.ApartmentInfo
import retrofit2.Response
import retrofit2.http.GET

interface ApartmentService {

    @GET("v3/f4864c66-ee04-4e7f-88a2-2fbd912ca5ab")
    suspend fun getApartments(): Response<ApartmentInfo>

}