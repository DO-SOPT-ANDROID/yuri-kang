package org.sopt.dosopttemplate.util

sealed interface UiState<out T> {
    object Loading : UiState<Nothing>

    data class Success<out T>(
        val data: T? = null,
    ) : UiState<T>

    data class Failure(
        val errorMessage: String,
    ) : UiState<Nothing>
}
