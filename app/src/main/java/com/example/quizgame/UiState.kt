package com.example.quizgame

import com.example.quizgame.databinding.ActivityMainBinding

interface UiState {

    fun update(binding: ActivityMainBinding)

    data class Question(
        private val questionUiState: QuestionUiState,
        private val choiceOneUiState: ChoiceUiState,
        private val choiceTwoUiState: ChoiceUiState,
        private val choiceThreeUiState: ChoiceUiState,
        private val choiceFourUiState: ChoiceUiState,
        private val actionUiState: ActionUiState
    ) : UiState {

        override fun update(binding: ActivityMainBinding) {
            TODO("Not yet implemented")
        }

    }

    data class ChoiceMade(
        private val choiceOneUiState: ChoiceUiState,
        private val choiceTwoUiState: ChoiceUiState,
        private val choiceThreeUiState: ChoiceUiState,
        private val choiceFourUiState: ChoiceUiState,
        private val actionUiState: ActionUiState
    ) : UiState {
        override fun update(binding: ActivityMainBinding) {
            TODO("Not yet implemented")
        }

    }

    data class Correct(
        private val choiceOneUiState: ChoiceUiState,
        private val choiceTwoUiState: ChoiceUiState,
        private val choiceThreeUiState: ChoiceUiState,
        private val choiceFourUiState: ChoiceUiState,
        private val actionUiState: ActionUiState
    ) : UiState {

        override fun update(binding: ActivityMainBinding) {
            TODO("Not yet implemented")
        }

    }

    data class Incorrect(
        private val choiceOneUiState: ChoiceUiState,
        private val choiceTwoUiState: ChoiceUiState,
        private val choiceThreeUiState: ChoiceUiState,
        private val choiceFourUiState: ChoiceUiState,
        private val actionUiState: ActionUiState
    ) : UiState {

        override fun update(binding: ActivityMainBinding) {
            TODO("Not yet implemented")
        }
    }

}