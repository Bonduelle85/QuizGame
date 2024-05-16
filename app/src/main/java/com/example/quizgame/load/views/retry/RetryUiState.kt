package com.example.quizgame.load.views.retry

import android.view.View
import java.io.Serializable

interface RetryUiState : Serializable {

    fun show(update: UpdateRetry)

    object Show : RetryUiState {

        override fun show(update: UpdateRetry) {
            update.updateUi(View.VISIBLE)
        }
    }

    object Hide : RetryUiState {

        override fun show(update: UpdateRetry) {
            update.updateUi(View.GONE)
        }
    }
}