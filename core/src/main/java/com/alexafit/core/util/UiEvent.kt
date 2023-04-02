package com.alexafit.core.util

sealed class UiEvent {
    object Success : UiEvent()
    data class ShowSnackbar(val message: UiText) : UiEvent()
    object Navigate : UiEvent()
    data class Loading(val isLoading: Boolean) : UiEvent()
}
