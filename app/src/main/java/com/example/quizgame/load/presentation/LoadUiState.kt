package com.example.quizgame.load.presentation

import com.example.quizgame.load.views.error.UpdateError
import com.example.quizgame.load.views.progress.UpdateProgress
import com.example.quizgame.load.views.retry.UpdateRetry

interface LoadUiState {
    fun update(progress: UpdateProgress, error: UpdateError, retry: UpdateRetry)
    fun navigate(exit: () -> Unit)

    data class Error(private val message: String) : LoadUiState {
        override fun update(progress: UpdateProgress, error: UpdateError, retry: UpdateRetry) {
            TODO("Not yet implemented")
        }

        override fun navigate(exit: () -> Unit) {
            TODO("Not yet implemented")
        }
    }

    object Progress : LoadUiState {
        override fun update(progress: UpdateProgress, error: UpdateError, retry: UpdateRetry) {
            TODO("Not yet implemented")
        }

        override fun navigate(exit: () -> Unit) {
            TODO("Not yet implemented")
        }
    }

    object Success : LoadUiState {
        override fun update(progress: UpdateProgress, error: UpdateError, retry: UpdateRetry) {
            TODO("Not yet implemented")
        }

        override fun navigate(exit: () -> Unit) {
            TODO("Not yet implemented")
        }
    }

}