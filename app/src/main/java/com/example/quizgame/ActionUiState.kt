package com.example.quizgame

import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatButton
interface ActionUiState {
    fun show(actionButton: AppCompatButton)
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
    object None : Abstract(View.INVISIBLE, R.string.check)
    object Check : Abstract(View.VISIBLE, R.string.check)
    object Next : Abstract(View.VISIBLE, R.string.next)
}