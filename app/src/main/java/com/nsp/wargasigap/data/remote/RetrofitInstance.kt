package com.nsp.wargasigap.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: BMKGApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://data.bmkg.go.id/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BMKGApiService::class.java)
    }
}