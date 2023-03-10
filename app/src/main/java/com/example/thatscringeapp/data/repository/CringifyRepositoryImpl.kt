package com.example.thatscringeapp.data.repository

import android.util.Log
import com.example.thatscringeapp.BuildConfig
import com.example.thatscringeapp.data.remote.OpenAiService
import com.example.thatscringeapp.data.remote.RequestBody
import com.example.thatscringeapp.domain.repository.CringifyRepository
import com.example.thatscringeapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class CringifyRepositoryImpl @Inject constructor(
    private val api: OpenAiService
) : CringifyRepository {

    override suspend fun getCringed(prompt: String): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())
            val apiKey = BuildConfig.API_KEY
            val requestBody = RequestBody(
                model = "text-davinci-003",
                prompt = "Create a cringy motivational quote based on the following topic.\n ${prompt.toString()}\n Cringy motivational quote:",
                maxTokens = 400,
                temperature = 1f
            )
            val apiResponse = api.getSomeCringe(
                apiKey = "Bearer ${apiKey.toString()}",
                requestBody = requestBody
            )
            emit(Resource.Success(apiResponse.body()?.choices?.get(0)?.text))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Http Exception occurred."))
            Log.e("CringeError", "Http Exception occurred.")
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "IO Exception occurred."))
            Log.e("CringeError", "IO Exception occurred.")
        }
    }
}