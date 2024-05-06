package com.example.quizgame.presentation.game

import com.example.quizgame.views.action.ActionUiState
import com.example.quizgame.views.action.UpdateActionButton
import com.example.quizgame.views.choice.ChoiceButtonAction
import com.example.quizgame.views.choice.ChoiceUiState
import com.example.quizgame.views.question.QuestionUiState
import com.example.quizgame.views.question.UpdateQuestionText

interface GameUiState {

    fun update(
        questionTextView: UpdateQuestionText,
        choiceButtonOne: ChoiceButtonAction,
        choiceButtonTwo: ChoiceButtonAction,
        choiceButtonThree: ChoiceButtonAction,
        choiceButtonFour: ChoiceButtonAction,
        actionButton: UpdateActionButton
    ) = Unit

    fun navigate(navigation: () -> Unit) = Unit


    object Empty : GameUiState

    object GoToStatistics : GameUiState {

        override fun navigate(navigation: () -> Unit) {
            navigation.invoke()
        }
    }

    abstract class Abstract(
        private val choiceOneUiState: ChoiceUiState,
        private val choiceTwoUiState: ChoiceUiState,
        private val choiceThreeUiState: ChoiceUiState,
        private val choiceFourUiState: ChoiceUiState,
        private val actionUiState: ActionUiState
    ) : GameUiState {
        override fun update(
            questionTextView: UpdateQuestionText,
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
            questionTextView: UpdateQuestionText,
            choiceButtonOne: ChoiceButtonAction,
            choiceButtonTwo: ChoiceButtonAction,
            choiceButtonThree: ChoiceButtonAction,
            choiceButtonFour: ChoiceButtonAction,
            actionButton: UpdateActionButton
        ) {
            super.update(
                questionTextView,
                choiceButtonOne,
                choiceButtonTwo,
                choiceButtonThree,
                choiceButtonFour,
                actionButton
            )
            questionUiState.show(questionTextView)
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