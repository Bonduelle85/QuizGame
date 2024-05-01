package com.example.quizgame

class MainViewModel(
    private val repository: Repository
) : Actions {

    fun init(isFirstTime: Boolean = true): UiState {
        return if (isFirstTime) {
            val data = repository.questionAndChoices()
            UiState.Question(
                QuestionUiState.Base(data.question),
                ChoiceUiState.AvailableToChoose(data.choiceOne),
                ChoiceUiState.AvailableToChoose(data.choiceTwo),
                ChoiceUiState.AvailableToChoose(data.choiceThree),
                ChoiceUiState.AvailableToChoose(data.choiceFour),
                ActionUiState.None
            )
        } else {
            UiState.Empty
        }
    }

    fun chooseFirst(): UiState {
        repository.chooseFirst()
        return UiState.ChoiceMade(
            choiceOneUiState = ChoiceUiState.ChoiceMade,
            choiceTwoUiState = ChoiceUiState.Empty,
            choiceThreeUiState = ChoiceUiState.Empty,
            choiceFourUiState = ChoiceUiState.Empty,
            actionUiState = ActionUiState.Check
        )
    }

    fun chooseSecond(): UiState {
        repository.chooseSecond()
        return UiState.ChoiceMade(
            choiceOneUiState = ChoiceUiState.Empty,
            choiceTwoUiState = ChoiceUiState.ChoiceMade,
            choiceThreeUiState = ChoiceUiState.Empty,
            choiceFourUiState = ChoiceUiState.Empty,
            actionUiState = ActionUiState.Check
        )
    }

    fun chooseThird(): UiState {
        repository.chooseThird()
        return UiState.ChoiceMade(
            choiceOneUiState = ChoiceUiState.Empty,
            choiceTwoUiState = ChoiceUiState.Empty,
            choiceThreeUiState = ChoiceUiState.ChoiceMade,
            choiceFourUiState = ChoiceUiState.Empty,
            actionUiState = ActionUiState.Check
        )
    }

    fun chooseFourth(): UiState {
        repository.chooseFourth()
        return UiState.ChoiceMade(
            choiceOneUiState = ChoiceUiState.Empty,
            choiceTwoUiState = ChoiceUiState.Empty,
            choiceThreeUiState = ChoiceUiState.Empty,
            choiceFourUiState = ChoiceUiState.ChoiceMade,
            actionUiState = ActionUiState.Check
        )
    }

    override fun check(): UiState {
        when (val result = repository.check()) {
            is CheckResult.Correct -> {
                return UiState.Correct(
                    choiceOneUiState = if (result.correctIndex == 0) ChoiceUiState.Correct else ChoiceUiState.NotAvailable,
                    choiceTwoUiState = if (result.correctIndex == 1) ChoiceUiState.Correct else ChoiceUiState.NotAvailable,
                    choiceThreeUiState = if (result.correctIndex == 2) ChoiceUiState.Correct else ChoiceUiState.NotAvailable,
                    choiceFourUiState = if (result.correctIndex == 3) ChoiceUiState.Correct else ChoiceUiState.NotAvailable,
                    actionUiState = ActionUiState.Next
                )
            }

            is CheckResult.Incorrect -> {
                return UiState.Incorrect(
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

    override fun next(): UiState {
        repository.next()
        return init()
    }
}

interface Actions {
    fun check(): UiState
    fun next(): UiState
}