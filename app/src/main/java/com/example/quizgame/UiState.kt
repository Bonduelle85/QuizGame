package com.example.quizgame

import com.example.quizgame.databinding.ActivityMainBinding

interface UiState {

    fun update(binding: ActivityMainBinding)

    abstract class Abstract(
        private val choiceOneUiState: ChoiceUiState,
        private val choiceTwoUiState: ChoiceUiState,
        private val choiceThreeUiState: ChoiceUiState,
        private val choiceFourUiState: ChoiceUiState,
        private val actionUiState: ActionUiState
    ) : UiState {
        override fun update(binding: ActivityMainBinding) {
            choiceOneUiState.show(binding.choiceOneButton)
            choiceTwoUiState.show(binding.choiceTwoButton)
            choiceThreeUiState.show(binding.choiceThreeButton)
            choiceFourUiState.show(binding.choiceFourButton)
            actionUiState.show(binding.actionButton)
        }
    }

    data class Question(
        private val questionUiState: QuestionUiState,
        private val choiceOneUiState: ChoiceUiState,
        private val choiceTwoUiState: ChoiceUiState,
        private val choiceThreeUiState: ChoiceUiState,
        private val choiceFourUiState: ChoiceUiState,
        private val actionUiState: ActionUiState
    ) : Abstract(
        choiceOneUiState,
        choiceTwoUiState,
        choiceThreeUiState,
        choiceFourUiState,
        actionUiState
    ) {
        override fun update(binding: ActivityMainBinding) {
            super.update(binding)
            questionUiState.show(binding.questionTextView)
        }
    }

    data class ChoiceMade(
        private val choiceOneUiState: ChoiceUiState,
        private val choiceTwoUiState: ChoiceUiState,
        private val choiceThreeUiState: ChoiceUiState,
        private val choiceFourUiState: ChoiceUiState,
        private val actionUiState: ActionUiState
    ) : Abstract(
        choiceOneUiState,
        choiceTwoUiState,
        choiceThreeUiState,
        choiceFourUiState,
        actionUiState
    )

    data class Correct(
        private val choiceOneUiState: ChoiceUiState,
        private val choiceTwoUiState: ChoiceUiState,
        private val choiceThreeUiState: ChoiceUiState,
        private val choiceFourUiState: ChoiceUiState,
        private val actionUiState: ActionUiState
    ) : Abstract(
        choiceOneUiState,
        choiceTwoUiState,
        choiceThreeUiState,
        choiceFourUiState,
        actionUiState
    )

    data class Incorrect(
        private val choiceOneUiState: ChoiceUiState,
        private val choiceTwoUiState: ChoiceUiState,
        private val choiceThreeUiState: ChoiceUiState,
        private val choiceFourUiState: ChoiceUiState,
        private val actionUiState: ActionUiState
    ) : Abstract(
        choiceOneUiState,
        choiceTwoUiState,
        choiceThreeUiState,
        choiceFourUiState,
        actionUiState
    )
}