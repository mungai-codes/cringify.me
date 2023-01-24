package com.example.thatscringeapp.data.remote.dto

data class Response(
    val choices: List<Choice>,
    val created: Int,
    val id: String,
    val model: String,
    val `object`: String,
    val usage: Usage
)