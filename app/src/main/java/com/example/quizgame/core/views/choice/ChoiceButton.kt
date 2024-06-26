package com.example.quizgame.core.views.choice

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat

class ChoiceButton : AppCompatButton, ChoiceButtonAction {

    private var choiceUiState: ChoiceUiState = ChoiceUiState.Empty

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttrs: Int) : super(
        context,
        attributeSet,
        defStyleAttrs
    )

    override fun updateUiState(outer: ChoiceUiState) {
        choiceUiState = outer
        choiceUiState.show(this)
    }

    override fun updateText(text: String) {
        setText(text)
    }

    override fun updateUi(colorResId: Int, clickable: Boolean) {
        setBackgroundColor(ContextCompat.getColor(context, colorResId))
        isClickable = clickable
    }

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val state = ChoiceSavedState(it)
            state.save(choiceUiState)
            return state
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as ChoiceSavedState
        super.onRestoreInstanceState(restoredState.superState)
        updateUiState(restoredState.restore())
    }

}

interface ChoiceButtonAction{
    fun updateUiState(outer: ChoiceUiState)

    fun updateText(text: String)
    fun updateUi(@ColorRes colorResId: Int, clickable: Boolean)
}