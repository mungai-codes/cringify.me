package com.example.thatscringeapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UsageDto(
    @SerializedName("completion_tokens")
    val completionTokens: Int,
    @SerializedName("prompt_tokens")
    val promptTokens: Int,
    @SerializedName("total_tokens")
    val totalTokens: Int
)