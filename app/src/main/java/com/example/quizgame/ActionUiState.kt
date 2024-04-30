package com.example.quizgame

import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatButton
import java.io.Serializable

interface ActionUiState : Serializable {

    fun show(actionButton: AppCompatButton)
    fun handleAction(viewModel: Actions): UiState

    abstract class Abstract(
        private val visibility: Int,
        @StringRes
        private val resourceId: Int
    ) : ActionUiState {
        override fun show(actionButton: AppCompatButton) = with(actionButton) {
            visibility = this@Abstract.visibility
            setText(resourceId)
        }
    }
    object None : Abstract(View.INVISIBLE, R.string.check) {
        override fun handleAction(viewModel: Actions): UiState = throw IllegalStateException("")
    }

    object Check : Abstract(View.VISIBLE, R.string.check) {
        override fun handleAction(viewModel: Actions): UiState = viewModel.check()
    }

    object Next : Abstract(View.VISIBLE, R.string.next) {
        override fun handleAction(viewModel: Actions): UiState = viewModel.next()
    }
}