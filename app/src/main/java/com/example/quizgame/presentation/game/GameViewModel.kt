package com.example.quizgame.presentation.game

import com.example.quizgame.data.CheckResult
import com.example.quizgame.data.Repository
import com.example.quizgame.views.action.ActionUiState
import com.example.quizgame.views.choice.ChoiceUiState
import com.example.quizgame.views.question.QuestionUiState

class GameViewModel(
    private val repository: Repository
) : Actions {

    fun init(isFirstTime: Boolean = true): GameUiState {
        return if (isFirstTime) {
            val data = repository.questionAndChoices()
            GameUiState.Question(
                QuestionUiState.Base(data.question),
                ChoiceUiState.AvailableToChoose(data.choiceOne),
                ChoiceUiState.AvailableToChoose(data.choiceTwo),
                ChoiceUiState.AvailableToChoose(data.choiceThree),
                ChoiceUiState.AvailableToChoose(data.choiceFour),
                ActionUiState.None
            )
        } else {
            GameUiState.Empty
        }
    }

    fun chooseFirst(): GameUiState {
        repository.chooseFirst()
        return GameUiState.ChoiceMade(
            choiceOneUiState = ChoiceUiState.ChoiceMade,
            choiceTwoUiState = ChoiceUiState.Empty,
            choiceThreeUiState = ChoiceUiState.Empty,
            choiceFourUiState = ChoiceUiState.Empty,
            actionUiState = ActionUiState.Check
        )
    }

    fun chooseSecond(): GameUiState {
        repository.chooseSecond()
        return GameUiState.ChoiceMade(
            choiceOneUiState = ChoiceUiState.Empty,
            choiceTwoUiState = ChoiceUiState.ChoiceMade,
            choiceThreeUiState = ChoiceUiState.Empty,
            choiceFourUiState = ChoiceUiState.Empty,
            actionUiState = ActionUiState.Check
        )
    }

    fun chooseThird(): GameUiState {
        repository.chooseThird()
        return GameUiState.ChoiceMade(
            choiceOneUiState = ChoiceUiState.Empty,
            choiceTwoUiState = ChoiceUiState.Empty,
            choiceThreeUiState = ChoiceUiState.ChoiceMade,
            choiceFourUiState = ChoiceUiState.Empty,
            actionUiState = ActionUiState.Check
        )
    }

    fun chooseFourth(): GameUiState {
        repository.chooseFourth()
        return GameUiState.ChoiceMade(
            choiceOneUiState = ChoiceUiState.Empty,
            choiceTwoUiState = ChoiceUiState.Empty,
            choiceThreeUiState = ChoiceUiState.Empty,
            choiceFourUiState = ChoiceUiState.ChoiceMade,
            actionUiState = ActionUiState.Check
        )
    }

    override fun check(): GameUiState {
        when (val result = repository.check()) {
            is CheckResult.Correct -> {
                return GameUiState.Correct(
                    choiceOneUiState = if (result.correctIndex == 0) ChoiceUiState.Correct else ChoiceUiState.NotAvailable,
                    choiceTwoUiState = if (result.correctIndex == 1) ChoiceUiState.Correct else ChoiceUiState.NotAvailable,
                    choiceThreeUiState = if (result.correctIndex == 2) ChoiceUiState.Correct else ChoiceUiState.NotAvailable,
                    choiceFourUiState = if (result.correctIndex == 3) ChoiceUiState.Correct else ChoiceUiState.NotAvailable,
                    actionUiState = ActionUiState.Next
                )
            }

            is CheckResult.Incorrect -> {
                return GameUiState.Incorrect(
                    choiceOneUiState = if (result.correctIndex == 0)
                        ChoiceUiState.Correct
                    else if (result.incorrectIndex == 0)
                        ChoiceUiState.Incorrect
                    else
                        ChoiceUiState.NotAvailable,
                    choiceTwoUiState = if (result.correctIndex == 1)
                        ChoiceUiState.Correct
                    else if (result.incorrectIndex == 1)
                        ChoiceUiState.Incorrect
                    else
                        ChoiceUiState.NotAvailable,
                    choiceThreeUiState = if (result.correctIndex == 2)
                        ChoiceUiState.Correct
                    else if (result.incorrectIndex == 2)
                        ChoiceUiState.Incorrect
                    else
                        ChoiceUiState.NotAvailable,
                    choiceFourUiState = if (result.correctIndex == 3)
                        ChoiceUiState.Correct
                    else if (result.incorrectIndex == 3)
                        ChoiceUiState.Incorrect
                    else
                        ChoiceUiState.NotAvailable,
                    actionUiState = ActionUiState.Next
                )
            }

            else -> throw IllegalStateException()
        }
    }

    override fun next(): GameUiState {
        repository.next()
        return if (repository.noMoreQuestions()) {
            GameUiState.GoToStatistics
        } else
            init()
    }
}

interface Actions {
    fun check(): GameUiState
    fun next(): GameUiState
}