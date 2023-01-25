package com.example.thatscringeapp.data.remote.dto

import androidx.annotation.Keep

@Keep
data class UsageDto(
    val completion_tokens: Int,
    val prompt_tokens: Int,
    val total_tokens: Int
)