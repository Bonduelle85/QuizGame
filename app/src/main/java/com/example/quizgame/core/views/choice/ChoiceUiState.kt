package com.example.quizgame.core.views.choice

import com.example.quizgame.R
import java.io.Serializable

interface ChoiceUiState : Serializable {
    fun show(choiceButton: ChoiceButtonAction)

    data class AvailableToChoose(private val value: String) : ChoiceUiState {
        override fun show(choiceButton: ChoiceButtonAction) = with(choiceButton) {
            updateText(value)
            updateUi(R.color.yellow, true)
        }
    }

    object ChoiceMade : ChoiceUiState {
        override fun show(choiceButton: ChoiceButtonAction) = with(choiceButton) {
            updateUi(R.color.purple, false)
        }
    }

    object Incorrect  : ChoiceUiState {
        override fun show(choiceButton: ChoiceButtonAction) = with(choiceButton) {
            updateUi(R.color.red, false)
        }
    }

    object Correct : ChoiceUiState {
        override fun show(choiceButton: ChoiceButtonAction) = with(choiceButton) {
            updateUi(R.color.green, false)
        }
    }

    object Empty : ChoiceUiState {
        override fun show(choiceButton: ChoiceButtonAction) = with(choiceButton) {
            updateUi(R.color.yellow, true)
        }
    }

    object NotAvailable : ChoiceUiState {
        override fun show(choiceButton: ChoiceButtonAction) = with(choiceButton) {
            updateUi(R.color.grey, false)
        }
    }
}