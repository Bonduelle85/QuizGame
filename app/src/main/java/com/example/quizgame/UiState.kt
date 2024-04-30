package com.example.quizgame

import com.example.quizgame.databinding.ActivityMainBinding
import java.io.Serializable

interface UiState : Serializable {

    fun update(binding: ActivityMainBinding)

    fun handleAction(viewModel: Actions): UiState

    abstract class Abstract(
        private val choiceOneUiState: ChoiceUiState,
        private val choiceTwoUiState: ChoiceUiState,
        private val choiceThreeUiState: ChoiceUiState,
        private val choiceFourUiState: ChoiceUiState,
        private val actionUiState: ActionUiState
    ) : UiState {
        override fun update(binding: ActivityMainBinding) = with(binding) {
            choiceOneButton.updateUiState(choiceOneUiState)
            choiceTwoButton.updateUiState(choiceTwoUiState)
            choiceThreeButton.updateUiState(choiceThreeUiState)
            choiceFourButton.updateUiState(choiceFourUiState)
            actionButton.updateUiState(actionUiState)
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

        override fun handleAction(viewModel: Actions): UiState = throw IllegalStateException("")
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
    ) {
        override fun handleAction(viewModel: Actions): UiState {
            return viewModel.check()
        }
    }

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
    ) {
        override fun handleAction(viewModel: Actions): UiState {
            return viewModel.next()
        }
    }

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
    ) {
        override fun handleAction(viewModel: Actions): UiState {
            return viewModel.next()
        }
    }
}