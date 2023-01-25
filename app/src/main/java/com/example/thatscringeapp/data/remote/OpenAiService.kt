package com.example.thatscringeapp.data.remote

import com.example.thatscringeapp.data.remote.dto.ResponseDto
import retrofit2.Response
import retrofit2.http.*

interface OpenAiService {

    @POST("completions")
    suspend fun getSomeCringe(
        @Header("Authorization") apiKey: String,
        @Body requestBody: RequestBody,
    ): Response<ResponseDto>

}
