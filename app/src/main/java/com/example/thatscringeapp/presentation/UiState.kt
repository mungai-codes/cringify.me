package com.example.thatscringeapp.presentation

import com.example.thatscringeapp.domain.model.Choice

data class UiState(
    val quotes: List<Choice> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val prompt: String = "",
    val quote: String = ""
)
