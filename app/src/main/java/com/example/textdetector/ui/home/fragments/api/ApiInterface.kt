package com.example.textdetector.ui.home.fragments.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/predict")
    fun sendReq(@Body requestModel: PredictionRequest) : Call<PredictionResponse>
}