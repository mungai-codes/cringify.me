package com.example.thatscringeapp.util

sealed class UiEvent {
    data class ShowSnackBar(val message: String) : UiEvent()
    data class ShowInfoToast(val message: String) : UiEvent()

    data class ShowIoSnackBar(val message: String): UiEvent()
}
