package com.example.quizgame.core.views.action

import android.view.View
import androidx.annotation.StringRes
import com.example.quizgame.R
import com.example.quizgame.game.presentation.Actions
import com.example.quizgame.game.presentation.GameUiState
import java.io.Serializable

interface ActionUiState : Serializable {

    fun show(actionButton: UpdateActionButton)
    fun handleAction(viewModel: Actions): GameUiState

    abstract class Abstract(
        private val visibility: Int,
        @StringRes
        private val resourceId: Int
    ) : ActionUiState {
        override fun show(actionButton: UpdateActionButton) = with(actionButton) {
            updateUi(resourceId, visibility)
        }
    }

    object None : Abstract(View.INVISIBLE, R.string.check) {
        override fun handleAction(viewModel: Actions): GameUiState = throw IllegalStateException("")
    }

    object Check : Abstract(View.VISIBLE, R.string.check) {
        override fun handleAction(viewModel: Actions): GameUiState = viewModel.check()
    }

    object Next : Abstract(View.VISIBLE, R.string.next) {
        override fun handleAction(viewModel: Actions): GameUiState = viewModel.next()
    }
}
