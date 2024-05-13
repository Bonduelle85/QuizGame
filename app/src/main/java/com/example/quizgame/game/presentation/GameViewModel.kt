package com.example.quizgame.game.presentation

import com.example.quizgame.core.data.CheckResult
import com.example.quizgame.core.views.action.ActionUiState
import com.example.quizgame.core.views.choice.ChoiceUiState
import com.example.quizgame.core.views.question.QuestionUiState
import com.example.quizgame.game.data.GameRepository
import com.example.quizgame.main.presentation.MyViewModel

class GameViewModel(
    private val gameRepository: GameRepository
) : MyViewModel, Actions {

    fun init(isFirstTime: Boolean = true): GameUiState {
        return if (isFirstTime) {
            gameRepository.saveCurrentScreenIsGame()
            val data = gameRepository.questionAndChoices()
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
        gameRepository.chooseFirst()
        return GameUiState.ChoiceMade(
            choiceOneUiState = ChoiceUiState.ChoiceMade,
            choiceTwoUiState = ChoiceUiState.Empty,
            choiceThreeUiState = ChoiceUiState.Empty,
            choiceFourUiState = ChoiceUiState.Empty,
            actionUiState = ActionUiState.Check
        )
    }

    fun chooseSecond(): GameUiState {
        gameRepository.chooseSecond()
        return GameUiState.ChoiceMade(
            choiceOneUiState = ChoiceUiState.Empty,
            choiceTwoUiState = ChoiceUiState.ChoiceMade,
            choiceThreeUiState = ChoiceUiState.Empty,
            choiceFourUiState = ChoiceUiState.Empty,
            actionUiState = ActionUiState.Check
        )
    }

    fun chooseThird(): GameUiState {
        gameRepository.chooseThird()
        return GameUiState.ChoiceMade(
            choiceOneUiState = ChoiceUiState.Empty,
            choiceTwoUiState = ChoiceUiState.Empty,
            choiceThreeUiState = ChoiceUiState.ChoiceMade,
            choiceFourUiState = ChoiceUiState.Empty,
            actionUiState = ActionUiState.Check
        )
    }

    fun chooseFourth(): GameUiState {
        gameRepository.chooseFourth()
        return GameUiState.ChoiceMade(
            choiceOneUiState = ChoiceUiState.Empty,
            choiceTwoUiState = ChoiceUiState.Empty,
            choiceThreeUiState = ChoiceUiState.Empty,
            choiceFourUiState = ChoiceUiState.ChoiceMade,
            actionUiState = ActionUiState.Check
        )
    }

    override fun check(): GameUiState {
        when (val result = gameRepository.check()) {
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
        gameRepository.next()
        return if (gameRepository.noMoreQuestions()) {
            GameUiState.GoToStatistics
        } else
            init()
    }
}

interface Actions {

    fun check(): GameUiState

    fun next(): GameUiState
}