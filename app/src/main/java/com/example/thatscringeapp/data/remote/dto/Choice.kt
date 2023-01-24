package com.example.thatscringeapp.data.remote.dto

data class Choice(
    val finish_reason: String,
    val index: Int,
    val logprobs: Boolean? = null,
    val text: String
)