package com.example.quizgame

import com.example.quizgame.core.data.CheckResult
import com.example.quizgame.core.data.QuestionAndChoices
import com.example.quizgame.core.views.action.ActionUiState
import com.example.quizgame.core.views.choice.ChoiceUiState
import com.example.quizgame.core.views.question.QuestionUiState
import com.example.quizgame.game.data.GameRepository
import com.example.quizgame.game.presentation.GameUiState
import com.example.quizgame.game.presentation.GameViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GameViewModelTest {

    private lateinit var viewModel: GameViewModel
    private lateinit var repository: FakeGameRepository

    @Before
    fun setup() {
        repository = FakeGameRepository()
        viewModel = GameViewModel(
            gameRepository = repository
        )
    }

    @Test
    fun caseNumberOne() {
        var actualGameUiState: GameUiState = viewModel.init()
        var expectedGameUiState: GameUiState = GameUiState.Question(
            questionUiState = QuestionUiState.Base(value = "question number 1"),
            choiceOneUiState = ChoiceUiState.AvailableToChoose(value = "q1 choice 1"),
            choiceTwoUiState = ChoiceUiState.AvailableToChoose(value = "q1 choice 2"),
            choiceThreeUiState = ChoiceUiState.AvailableToChoose(value = "q1 choice 3"),
            choiceFourUiState = ChoiceUiState.AvailableToChoose(value = "q1 choice 4"),
            actionUiState = ActionUiState.None
        )
        assertEquals(expectedGameUiState, actualGameUiState)

        actualGameUiState = viewModel.chooseFirst()
        expectedGameUiState = GameUiState.ChoiceMade(
            choiceOneUiState = ChoiceUiState.ChoiceMade,
            choiceTwoUiState = ChoiceUiState.Empty,
            choiceThreeUiState = ChoiceUiState.Empty,
            choiceFourUiState = ChoiceUiState.Empty,
            actionUiState = ActionUiState.Check
        )
        assertEquals(expectedGameUiState, actualGameUiState)

        actualGameUiState = viewModel.check()
        expectedGameUiState = GameUiState.Correct(
            choiceOneUiState = ChoiceUiState.Correct,
            choiceTwoUiState = ChoiceUiState.NotAvailable,
            choiceThreeUiState = ChoiceUiState.NotAvailable,
            choiceFourUiState = ChoiceUiState.NotAvailable,
            actionUiState = ActionUiState.Next
        )
        assertEquals(expectedGameUiState, actualGameUiState)

        actualGameUiState = viewModel.next()
        expectedGameUiState = GameUiState.Question(
            questionUiState = QuestionUiState.Base(value = "question number 2"),
            choiceOneUiState = ChoiceUiState.AvailableToChoose(value = "q2 choice 1"),
            choiceTwoUiState = ChoiceUiState.AvailableToChoose(value = "q2 choice 2"),
            choiceThreeUiState = ChoiceUiState.AvailableToChoose(value = "q2 choice 3"),
            choiceFourUiState = ChoiceUiState.AvailableToChoose(value = "q2 choice 4"),
            actionUiState = ActionUiState.None
        )
        assertEquals(expectedGameUiState, actualGameUiState)
    }

    @Test
    fun caseNumberTwo() {
        var actualGameUiState: GameUiState = viewModel.init()
        var expectedGameUiState: GameUiState = GameUiState.Question(
            questionUiState = QuestionUiState.Base(value = "question number 1"),
            choiceOneUiState = ChoiceUiState.AvailableToChoose(value = "q1 choice 1"),
            choiceTwoUiState = ChoiceUiState.AvailableToChoose(value = "q1 choice 2"),
            choiceThreeUiState = ChoiceUiState.AvailableToChoose(value = "q1 choice 3"),
            choiceFourUiState = ChoiceUiState.AvailableToChoose(value = "q1 choice 4"),
            actionUiState = ActionUiState.None
        )
        assertEquals(expectedGameUiState, actualGameUiState)

        actualGameUiState = viewModel.chooseSecond()
        expectedGameUiState = GameUiState.ChoiceMade(
            choiceOneUiState = ChoiceUiState.Empty,
            choiceTwoUiState = ChoiceUiState.ChoiceMade,
            choiceThreeUiState = ChoiceUiState.Empty,
            choiceFourUiState = ChoiceUiState.Empty,
            actionUiState = ActionUiState.Check
        )
        assertEquals(expectedGameUiState, actualGameUiState)

        actualGameUiState = viewModel.check()
        expectedGameUiState = GameUiState.Incorrect(
            choiceOneUiState = ChoiceUiState.Correct,
            choiceTwoUiState = ChoiceUiState.Incorrect,
            choiceThreeUiState = ChoiceUiState.NotAvailable,
            choiceFourUiState = ChoiceUiState.NotAvailable,
            actionUiState = ActionUiState.Next
        )
        assertEquals(expectedGameUiState, actualGameUiState)

        actualGameUiState = viewModel.next()
        expectedGameUiState = GameUiState.Question(
            questionUiState = QuestionUiState.Base(value = "question number 2"),
            choiceOneUiState = ChoiceUiState.AvailableToChoose(value = "q2 choice 1"),
            choiceTwoUiState = ChoiceUiState.AvailableToChoose(value = "q2 choice 2"),
            choiceThreeUiState = ChoiceUiState.AvailableToChoose(value = "q2 choice 3"),
            choiceFourUiState = ChoiceUiState.AvailableToChoose(value = "q2 choice 4"),
            actionUiState = ActionUiState.None
        )
        assertEquals(expectedGameUiState, actualGameUiState)
    }

    @Test
    fun caseNumberThree() {
        var actualGameUiState: GameUiState = viewModel.init()
        var expectedGameUiState: GameUiState = GameUiState.Question(
            questionUiState = QuestionUiState.Base(value = "question number 1"),
            choiceOneUiState = ChoiceUiState.AvailableToChoose(value = "q1 choice 1"),
            choiceTwoUiState = ChoiceUiState.AvailableToChoose(value = "q1 choice 2"),
            choiceThreeUiState = ChoiceUiState.AvailableToChoose(value = "q1 choice 3"),
            choiceFourUiState = ChoiceUiState.AvailableToChoose(value = "q1 choice 4"),
            actionUiState = ActionUiState.None
        )
        assertEquals(expectedGameUiState, actualGameUiState)

        actualGameUiState = viewModel.chooseFirst()
        expectedGameUiState = GameUiState.ChoiceMade(
            choiceOneUiState = ChoiceUiState.ChoiceMade,
            choiceTwoUiState = ChoiceUiState.Empty,
            choiceThreeUiState = ChoiceUiState.Empty,
            choiceFourUiState = ChoiceUiState.Empty,
            actionUiState = ActionUiState.Check
        )
        assertEquals(expectedGameUiState, actualGameUiState)

        actualGameUiState = viewModel.chooseSecond()
        expectedGameUiState = GameUiState.ChoiceMade(
            choiceOneUiState = ChoiceUiState.Empty,
            choiceTwoUiState = ChoiceUiState.ChoiceMade,
            choiceThreeUiState = ChoiceUiState.Empty,
            choiceFourUiState = ChoiceUiState.Empty,
            actionUiState = ActionUiState.Check
        )
        assertEquals(expectedGameUiState, actualGameUiState)

        actualGameUiState = viewModel.chooseThird()
        expectedGameUiState = GameUiState.ChoiceMade(
            choiceOneUiState = ChoiceUiState.Empty,
            choiceTwoUiState = ChoiceUiState.Empty,
            choiceThreeUiState = ChoiceUiState.ChoiceMade,
            choiceFourUiState = ChoiceUiState.Empty,
            actionUiState = ActionUiState.Check
        )
        assertEquals(expectedGameUiState, actualGameUiState)

        actualGameUiState = viewModel.chooseFourth()
        expectedGameUiState = GameUiState.ChoiceMade(
            choiceOneUiState = ChoiceUiState.Empty,
            choiceTwoUiState = ChoiceUiState.Empty,
            choiceThreeUiState = ChoiceUiState.Empty,
            choiceFourUiState = ChoiceUiState.ChoiceMade,
            actionUiState = ActionUiState.Check
        )
        assertEquals(expectedGameUiState, actualGameUiState)
    }
}

private class FakeGameRepository : GameRepository {

    private val questionAndChoicesList = listOf(
        QuestionAndChoices(
            question = "question number 1",
            choiceOne = "q1 choice 1",
            choiceTwo = "q1 choice 2",
            choiceThree = "q1 choice 3",
            choiceFour = "q1 choice 4",
        ),
        QuestionAndChoices(
            question = "question number 2",
            choiceOne = "q2 choice 1",
            choiceTwo = "q2 choice 2",
            choiceThree = "q2 choice 3",
            choiceFour = "q2 choice 4",
        ),
    )

    private var currentIndex = 0
    private var userChoiceIndex = -1

    override fun questionAndChoices(): QuestionAndChoices {
        return questionAndChoicesList[currentIndex]
    }
    override fun chooseFirst() {
        userChoiceIndex = 0
    }
    override fun chooseSecond() {
        userChoiceIndex = 1
    }
    override fun chooseThird() {
        userChoiceIndex = 2
    }
    override fun chooseFourth() {
        userChoiceIndex = 3
    }
    override fun check(): CheckResult {
        return if (userChoiceIndex == 0)
            CheckResult.Correct(correctIndex = 0)
        else
            CheckResult.Incorrect(correctIndex = 0, incorrectIndex = userChoiceIndex)
    }
    override fun next() {
        currentIndex++
        userChoiceIndex = -1
    }

    override fun noMoreQuestions(): Boolean {
        return true
    }

    override fun saveCurrentScreenIsGame() {
        //todo
    }
}