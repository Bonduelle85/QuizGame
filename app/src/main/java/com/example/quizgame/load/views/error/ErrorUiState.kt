package com.example.quizgame.load.views.error

import android.view.View
import java.io.Serializable

interface ErrorUiState : Serializable {

    fun show(updateError: UpdateError)

    object Hide : ErrorUiState {

        override fun show(updateError: UpdateError) {
            updateError.updateUi("", View.GONE)
        }
    }

    data class Show(private val message: String) : ErrorUiState {

        override fun show(updateError: UpdateError) {
            updateError.updateUi(message, View.VISIBLE)
        }
    }
}