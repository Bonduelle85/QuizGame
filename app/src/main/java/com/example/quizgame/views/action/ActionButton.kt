package com.example.quizgame.views.action

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatButton
import com.example.quizgame.presentation.game.Actions
import com.example.quizgame.presentation.game.GameUiState

class ActionButton : AppCompatButton, UpdateActionButton {

    private lateinit var uiState: ActionUiState

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttrs: Int) : super(
        context,
        attributeSet,
        defStyleAttrs
    )

    fun handleAction(viewModel: Actions): GameUiState = uiState.handleAction(viewModel)

    override fun updateUiState(outer: ActionUiState) {
        uiState = outer
        uiState.show(this)
    }

    override fun updateUi(textId: Int, visibility: Int) {
        setText(textId)
        this.visibility = visibility
    }

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let { superState ->
            val state = ActionSavedState(superState)
            state.save(uiState)
            return state
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as ActionSavedState
        super.onRestoreInstanceState(restoredState.superState)
        updateUiState(restoredState.restore())
    }
}

interface UpdateActionButton {
    fun updateUiState(outer: ActionUiState)
    fun updateUi(@StringRes textId: Int, visibility: Int)
}