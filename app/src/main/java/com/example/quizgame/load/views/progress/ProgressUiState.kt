package com.example.quizgame.load.views.progress

import android.view.View
import java.io.Serializable

interface ProgressUiState : Serializable {

    fun show(updateProgress: UpdateProgress)

    object Show : ProgressUiState {

        override fun show(updateProgress: UpdateProgress) {
            updateProgress.updateUi(View.VISIBLE)
        }
    }

    object Hide : ProgressUiState {

        override fun show(updateProgress: UpdateProgress) {
            updateProgress.updateUi(View.GONE)
        }
    }
}