package com.example.thatscringeapp.data.remote

import com.example.thatscringeapp.BuildConfig
import com.example.thatscringeapp.data.remote.dto.ResponseDto
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface OpenAiService {
    @POST("v1/engines/curie/completions")
    @Headers("Content-Type: application/json")
    suspend fun getSomeCringe(
        @Header("Authorization") apiKey: String = BuildConfig.API_KEY,
        @Query("prompt") prompt: String,
        @Query("temperature") temperature: Float = 0.5f,
        @Query("max_tokens") max_tokens: Int = 20
    ): ResponseDto

}