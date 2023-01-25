package com.example.thatscringeapp.data.remote.dto

import androidx.annotation.Keep

@Keep
data class ErrorResponse(
    val error: Error
)

@Keep
data class Error(
    val message: String,
    val code: String
)