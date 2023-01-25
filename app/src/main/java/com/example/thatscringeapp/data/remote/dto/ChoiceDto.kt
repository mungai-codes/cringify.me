package com.example.thatscringeapp.data.remote.dto

import androidx.annotation.Keep

@Keep
data class ChoiceDto(
    val finish_reason: String,
    val index: Int,
    val logprobs: Boolean?,
    val text: String
)
