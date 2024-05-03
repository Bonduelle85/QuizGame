package com.example.quizgame.views.choice

import com.example.quizgame.R
import java.io.Serializable

interface ChoiceUiState : Serializable {
    fun show(choiceButton: ChoiceButtonAction)

    data class AvailableToChoose(private val value: String) : ChoiceUiState {
        override fun show(choiceButton: ChoiceButtonAction) = with(choiceButton) {
            updateText(value)
            updateBackgroundColor(R.color.yellow)
            updateClickable(true)
        }
    }

    object ChoiceMade : ChoiceUiState {
        override fun show(choiceButton: ChoiceButtonAction) = with(choiceButton) {
            updateBackgroundColor(R.color.purple)
            updateClickable(false)
        }
    }

    object Incorrect  : ChoiceUiState {
        override fun show(choiceButton: ChoiceButtonAction) = with(choiceButton) {
            updateBackgroundColor(R.color.red)
        }
    }

    object Correct : ChoiceUiState {
        override fun show(choiceButton: ChoiceButtonAction) = with(choiceButton) {
            updateBackgroundColor(R.color.green)
            updateClickable(false)
        }
    }

    object Empty : ChoiceUiState {
        override fun show(choiceButton: ChoiceButtonAction) = with(choiceButton) {
            updateBackgroundColor(R.color.yellow)
            updateClickable(true)
        }
    }

    object NotAvailable : ChoiceUiState {
        override fun show(choiceButton: ChoiceButtonAction) = with(choiceButton) {
            updateBackgroundColor(R.color.grey)
            updateClickable(false)
        }
    }
}