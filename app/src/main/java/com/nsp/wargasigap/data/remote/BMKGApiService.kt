package com.nsp.wargasigap.data.remote

import com.nsp.wargasigap.data.model.GempaListResponse
import com.nsp.wargasigap.data.model.GempaResponse
import retrofit2.http.GET

interface BMKGApiService {

    @GET("DataMKG/TEWS/gempaterkini.json")
    suspend fun getGempaList(): GempaListResponse
}