package com.example.thatscringeapp.data.remote.dto

import androidx.annotation.Keep

@Keep
data class ResponseDto(
    val choices: List<ChoiceDto>,
    val created: Int,
    val id: String,
    val model: String,
    val `object`: String,
    val usage: UsageDto
)

