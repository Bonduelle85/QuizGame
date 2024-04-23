package com.example.quizgame

interface ChoiceUiState {

    object ChoiceMade : ChoiceUiState {

    }

    object Empty : ChoiceUiState {

    }

    object NotAvailable : ChoiceUiState {

    }

    object Correct : ChoiceUiState {

    }

    object Incorrect  : ChoiceUiState{

    }

    data class AvailableToChoose(private val value: String) : ChoiceUiState {

    }
}