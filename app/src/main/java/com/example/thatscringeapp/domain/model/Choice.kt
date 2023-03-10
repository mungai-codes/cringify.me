package com.example.thatscringeapp.domain.model

import com.google.gson.annotations.SerializedName

data class Choice(
    @SerializedName("finish_reason")
    val finishReason: String,
    val index: Int,
    @SerializedName("logprobs")
    val logProbs: Boolean?,
    val text: String
)
