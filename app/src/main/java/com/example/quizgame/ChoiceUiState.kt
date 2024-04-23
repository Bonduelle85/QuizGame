package com.example.quizgame

import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat

interface ChoiceUiState {
    fun show(choiceButton: AppCompatButton)

    data class AvailableToChoose(private val value: String) : ChoiceUiState {
        override fun show(choiceButton: AppCompatButton) = with(choiceButton) {
            text = value
            setBackgroundColor(ContextCompat.getColor(choiceButton.context, R.color.yellow))
            isClickable = true
        }
    }

    object ChoiceMade : ChoiceUiState {
        override fun show(choiceButton: AppCompatButton) = with(choiceButton) {
            setBackgroundColor(ContextCompat.getColor(choiceButton.context, R.color.purple))
            isClickable = false
        }
    }

    object Correct : ChoiceUiState {
        override fun show(choiceButton: AppCompatButton) = with(choiceButton) {
            setBackgroundColor(ContextCompat.getColor(choiceButton.context, R.color.green))
            isClickable = false
        }
    }

    object Incorrect  : ChoiceUiState{
        override fun show(choiceButton: AppCompatButton) = with(choiceButton) {
            setBackgroundColor(ContextCompat.getColor(choiceButton.context, R.color.red))
        }
    }

    object Empty : ChoiceUiState {
        override fun show(choiceButton: AppCompatButton) = with(choiceButton) {
            setBackgroundColor(ContextCompat.getColor(choiceButton.context, R.color.yellow))
            isClickable = true
        }
    }

    object NotAvailable : ChoiceUiState {
        override fun show(choiceButton: AppCompatButton) = with(choiceButton) {
            setBackgroundColor(ContextCompat.getColor(choiceButton.context, R.color.grey))
            isClickable = false
        }
    }
}