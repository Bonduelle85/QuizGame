package com.example.quizgame.load.presentation

import com.example.quizgame.core.presentation.UiState
import com.example.quizgame.load.views.error.ErrorUiState
import com.example.quizgame.load.views.error.UpdateError
import com.example.quizgame.load.views.progress.ProgressUiState
import com.example.quizgame.load.views.progress.UpdateProgress
import com.example.quizgame.load.views.retry.RetryUiState
import com.example.quizgame.load.views.retry.UpdateRetry

interface LoadUiState : UiState {
    fun update(progress: UpdateProgress, error: UpdateError, retry: UpdateRetry)
    fun navigate(exit: () -> Unit)

    data class Error(private val message: String) : LoadUiState {
        override fun update(progress: UpdateProgress, error: UpdateError, retry: UpdateRetry) {
            progress.updateUiState(ProgressUiState.Hide)
            error.updateUiState(ErrorUiState.Show(message))
            retry.updateUiState(RetryUiState.Show)
        }

        override fun navigate(exit: () -> Unit) = Unit
    }

    object Progress : LoadUiState {
        override fun update(progress: UpdateProgress, error: UpdateError, retry: UpdateRetry) {
            progress.updateUiState(ProgressUiState.Show)
            error.updateUiState(ErrorUiState.Hide)
            retry.updateUiState(RetryUiState.Hide)
        }

        override fun navigate(exit: () -> Unit) = Unit
    }

    object Success : LoadUiState {
        override fun update(progress: UpdateProgress, error: UpdateError, retry: UpdateRetry) = Unit

        override fun navigate(exit: () -> Unit) {
            exit.invoke()
        }
    }

}