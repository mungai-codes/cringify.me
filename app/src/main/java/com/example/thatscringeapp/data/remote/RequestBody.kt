package com.example.thatscringeapp.data.remote

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class RequestBody(
    @SerializedName("model")
    val model: String,
    @SerializedName("prompt")
    val prompt: String,
    @SerializedName("max_tokens")
    val maxTokens: Int,
    @SerializedName("temperature")
    val temperature: Float
)