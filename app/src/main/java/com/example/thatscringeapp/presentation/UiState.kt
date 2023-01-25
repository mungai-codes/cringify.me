package com.example.thatscringeapp.presentation

data class UiState(
    val quote: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val prompt: String = "",
    val visible: Boolean = false
)
