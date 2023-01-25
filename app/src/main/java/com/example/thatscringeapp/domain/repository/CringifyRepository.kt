package com.example.thatscringeapp.domain.repository

import com.example.thatscringeapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface CringifyRepository {
    suspend fun getCringed(prompt: String): Flow<Resource<String>>
}