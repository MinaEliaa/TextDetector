package com.example.textdetector.ui.home.fragments.api

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/predict")
    suspend fun predict(@Body requestBody: Map<String, String>): PredictionResponse

}