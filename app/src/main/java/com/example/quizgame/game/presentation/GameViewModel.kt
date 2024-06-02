package com.example.quizgame.game.presentation

import com.example.quizgame.core.data.CheckResult
import com.example.quizgame.core.views.action.ActionUiState
import com.example.quizgame.core.views.choice.ChoiceUiState
import com.example.quizgame.core.views.question.QuestionUiState
import com.example.quizgame.game.data.GameRepository
import com.example.quizgame.main.presentation.MyViewModel
import com.example.quizgame.main.presentation.RunAsync

class GameViewModel(
    runAsync: RunAsync,
    private val repository: GameRepository,
    private val uiObservable: GameUiObservable,
) : MyViewModel.Abstract(runAsync), Actions {

    fun init(isFirstTime: Boolean = true) {
        if (isFirstTime) {
            repository.saveCurrentScreenIsGame()
            runAsync({
                val data = repository.questionAndChoices()
                GameUiState.Question(
                    QuestionUiState.Base(data.question),
                    ChoiceUiState.AvailableToChoose(data.choiceOne),
                    ChoiceUiState.AvailableToChoose(data.choiceTwo),
                    ChoiceUiState.AvailableToChoose(data.choiceThree),
                    ChoiceUiState.AvailableToChoose(data.choiceFour),
                    ActionUiState.None
                )
            }) {
                uiObservable.updateUiState(it)
            }
        }
    }

    fun chooseFirst() {
        repository.chooseFirst()
        uiObservable.updateUiState(
            GameUiState.ChoiceMade(
                choiceOneUiState = ChoiceUiState.ChoiceMade,
                choiceTwoUiState = ChoiceUiState.Empty,
                choiceThreeUiState = ChoiceUiState.Empty,
                choiceFourUiState = ChoiceUiState.Empty,
                actionUiState = ActionUiState.Check
            )
        )
    }

    fun chooseSecond() {
        repository.chooseSecond()
        uiObservable.updateUiState(
            GameUiState.ChoiceMade(
                choiceOneUiState = ChoiceUiState.Empty,
                choiceTwoUiState = ChoiceUiState.ChoiceMade,
                choiceThreeUiState = ChoiceUiState.Empty,
                choiceFourUiState = ChoiceUiState.Empty,
                actionUiState = ActionUiState.Check
            )
        )
    }

    fun chooseThird() {
        repository.chooseThird()
        uiObservable.updateUiState(
            GameUiState.ChoiceMade(
                choiceOneUiState = ChoiceUiState.Empty,
                choiceTwoUiState = ChoiceUiState.Empty,
                choiceThreeUiState = ChoiceUiState.ChoiceMade,
                choiceFourUiState = ChoiceUiState.Empty,
                actionUiState = ActionUiState.Check
            )
        )
    }

    fun chooseFourth() {
        repository.chooseFourth()
        uiObservable.updateUiState(
            GameUiState.ChoiceMade(
                choiceOneUiState = ChoiceUiState.Empty,
                choiceTwoUiState = ChoiceUiState.Empty,
                choiceThreeUiState = ChoiceUiState.Empty,
                choiceFourUiState = ChoiceUiState.ChoiceMade,
                actionUiState = ActionUiState.Check
            )
        )
    }

    override fun check() {
        when (val result = repository.check()) {
            is CheckResult.Correct -> {
                uiObservable.updateUiState(
                    GameUiState.Correct(
                        choiceOneUiState = if (result.correctIndex == 0) ChoiceUiState.Correct else ChoiceUiState.NotAvailable,
                        choiceTwoUiState = if (result.correctIndex == 1) ChoiceUiState.Correct else ChoiceUiState.NotAvailable,
                        choiceThreeUiState = if (result.correctIndex == 2) ChoiceUiState.Correct else ChoiceUiState.NotAvailable,
                        choiceFourUiState = if (result.correctIndex == 3) ChoiceUiState.Correct else ChoiceUiState.NotAvailable,
                        actionUiState = ActionUiState.Next
                    )
                )
            }

            is CheckResult.Incorrect -> {
                uiObservable.updateUiState(
                    GameUiState.Incorrect(
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
                )
            }
        }
    }

    override fun next() {
        repository.next()
        if (repository.noMoreQuestions()) {
            uiObservable.updateUiState(GameUiState.GoToStatistics)
        } else
            init()
    }

    fun startGettingUpdates(observer: (GameUiState) -> Unit) {
        uiObservable.updateObserver(observer)
    }

    fun stopGettingUpdates() {
        uiObservable.clearObserver()
    }
}

interface Actions {

    fun check()

    fun next()
}