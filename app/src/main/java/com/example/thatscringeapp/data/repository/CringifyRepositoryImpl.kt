package com.example.thatscringeapp.data.repository

import android.util.Log
import com.example.thatscringeapp.data.remote.OpenAiService
import com.example.thatscringeapp.data.util.toChoice
import com.example.thatscringeapp.domain.model.Choice
import com.example.thatscringeapp.domain.repository.CringifyRepository
import com.example.thatscringeapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CringifyRepositoryImpl @Inject constructor(
    private val api: OpenAiService
) : CringifyRepository {

    override suspend fun getCringed(prompt: String): Flow<Resource<List<Choice>>> = flow {
        emit(Resource.Loading())
        try {
            val cringe = api.getSomeCringe(prompt = prompt)
            emit(Resource.Success(cringe.toChoice()))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Http Exception occurred."))
            Log.e("CringeError", "Http Exception occurred.")
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "IO Exception occurred."))
            Log.e("CringeError", "IO Exception occurred.")
        }
    }
}