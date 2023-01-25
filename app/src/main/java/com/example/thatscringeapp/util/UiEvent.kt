package com.example.thatscringeapp.util

sealed class UiEvent {
    data class ShowSnackBar(val message: String) : UiEvent()
}
