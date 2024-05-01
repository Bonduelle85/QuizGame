package com.example.quizgame

interface UiState {

    fun update(
        updateText: UpdateText,
        choiceButtonOne: ChoiceButtonAction,
        choiceButtonTwo: ChoiceButtonAction,
        choiceButtonThree: ChoiceButtonAction,
        choiceButtonFour: ChoiceButtonAction,
        actionButton: UpdateActionButton
    )

    object Empty : UiState {
        override fun update(
            updateText: UpdateText,
            choiceButtonOne: ChoiceButtonAction,
            choiceButtonTwo: ChoiceButtonAction,
            choiceButtonThree: ChoiceButtonAction,
            choiceButtonFour: ChoiceButtonAction,
            actionButton: UpdateActionButton
        ) = Unit
    }

    abstract class Abstract(
        private val choiceOneUiState: ChoiceUiState,
        private val choiceTwoUiState: ChoiceUiState,
        private val choiceThreeUiState: ChoiceUiState,
        private val choiceFourUiState: ChoiceUiState,
        private val actionUiState: ActionUiState
    ) : UiState {
        override fun update(
            updateText: UpdateText,
            choiceButtonOne: ChoiceButtonAction,
            choiceButtonTwo: ChoiceButtonAction,
            choiceButtonThree: ChoiceButtonAction,
            choiceButtonFour: ChoiceButtonAction,
            actionButton: UpdateActionButton
        ) {
            choiceButtonOne.updateUiState(choiceOneUiState)
            choiceButtonTwo.updateUiState(choiceTwoUiState)
            choiceButtonThree.updateUiState(choiceThreeUiState)
            choiceButtonFour.updateUiState(choiceFourUiState)
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
        override fun update(
            updateText: UpdateText,
            choiceButtonOne: ChoiceButtonAction,
            choiceButtonTwo: ChoiceButtonAction,
            choiceButtonThree: ChoiceButtonAction,
            choiceButtonFour: ChoiceButtonAction,
            actionButton: UpdateActionButton
        ) {
            super.update(
                updateText,
                choiceButtonOne,
                choiceButtonTwo,
                choiceButtonThree,
                choiceButtonFour,
                actionButton
            )
            questionUiState.show(updateText)
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