package com.example.quizgame

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private lateinit var repository: FakeRepository

    @Before
    fun setup() {
        repository = FakeRepository()
        viewModel = MainViewModel(
            repository = repository
        )
    }

    @Test
    fun caseNumberOne() {
        var actualUiState: UiState = viewModel.init()
        var expectedUiState: UiState = UiState.Question(
            questionUiState = QuestionUiState.Base(value = "question number 1"),
            choiceOneUiState = ChoiceUiState.AvailableToChoose(value = "q1 choice 1"),
            choiceTwoUiState = ChoiceUiState.AvailableToChoose(value = "q1 choice 2"),
            choiceThreeUiState = ChoiceUiState.AvailableToChoose(value = "q1 choice 3"),
            choiceFourUiState = ChoiceUiState.AvailableToChoose(value = "q1 choice 4"),
            actionUiState = ActionUiState.None
        )
        assertEquals(expectedUiState, actualUiState)

        actualUiState = viewModel.chooseFirst()
        expectedUiState = UiState.ChoiceMade(
            choiceOneUiState = ChoiceUiState.ChoiceMade,
            choiceTwoUiState = ChoiceUiState.Empty,
            choiceThreeUiState = ChoiceUiState.Empty,
            choiceFourUiState = ChoiceUiState.Empty,
            actionUiState = ActionUiState.Check
        )
        assertEquals(expectedUiState, actualUiState)

        actualUiState = viewModel.handleAction()
        expectedUiState = UiState.Correct(
            choiceOneUiState = ChoiceUiState.Correct,
            choiceTwoUiState = ChoiceUiState.NotAvailable,
            choiceThreeUiState = ChoiceUiState.NotAvailable,
            choiceFourUiState = ChoiceUiState.NotAvailable,
            actionUiState = ActionUiState.Next
        )
        assertEquals(expectedUiState, actualUiState)

        actualUiState = viewModel.handleAction()
        expectedUiState = UiState.Question(
            questionUiState = QuestionUiState.Base(value = "question number 2"),
            choiceOneUiState = ChoiceUiState.AvailableToChoose(value = "q2 choice 1"),
            choiceTwoUiState = ChoiceUiState.AvailableToChoose(value = "q2 choice 2"),
            choiceThreeUiState = ChoiceUiState.AvailableToChoose(value = "q2 choice 3"),
            choiceFourUiState = ChoiceUiState.AvailableToChoose(value = "q2 choice 4"),
            actionUiState = ActionUiState.None
        )
        assertEquals(expectedUiState, actualUiState)
    }

    @Test
    fun caseNumberTwo() {
        var actualUiState: UiState = viewModel.init()
        var expectedUiState: UiState = UiState.Question(
            questionUiState = QuestionUiState.Base(value = "question number 1"),
            choiceOneUiState = ChoiceUiState.AvailableToChoose(value = "q1 choice 1"),
            choiceTwoUiState = ChoiceUiState.AvailableToChoose(value = "q1 choice 2"),
            choiceThreeUiState = ChoiceUiState.AvailableToChoose(value = "q1 choice 3"),
            choiceFourUiState = ChoiceUiState.AvailableToChoose(value = "q1 choice 4"),
            actionUiState = ActionUiState.None
        )
        assertEquals(expectedUiState, actualUiState)

        actualUiState = viewModel.chooseSecond()
        expectedUiState = UiState.ChoiceMade(
            choiceOneUiState = ChoiceUiState.Empty,
            choiceTwoUiState = ChoiceUiState.ChoiceMade,
            choiceThreeUiState = ChoiceUiState.Empty,
            choiceFourUiState = ChoiceUiState.Empty,
            actionUiState = ActionUiState.Check
        )
        assertEquals(expectedUiState, actualUiState)

        actualUiState = viewModel.handleAction()
        expectedUiState = UiState.Incorrect(
            choiceOneUiState = ChoiceUiState.Correct,
            choiceTwoUiState = ChoiceUiState.Incorrect,
            choiceThreeUiState = ChoiceUiState.NotAvailable,
            choiceFourUiState = ChoiceUiState.NotAvailable,
            actionUiState = ActionUiState.Next
        )
        assertEquals(expectedUiState, actualUiState)

        actualUiState = viewModel.handleAction()
        expectedUiState = UiState.Question(
            questionUiState = QuestionUiState.Base(value = "question number 2"),
            choiceOneUiState = ChoiceUiState.AvailableToChoose(value = "q2 choice 1"),
            choiceTwoUiState = ChoiceUiState.AvailableToChoose(value = "q2 choice 2"),
            choiceThreeUiState = ChoiceUiState.AvailableToChoose(value = "q2 choice 3"),
            choiceFourUiState = ChoiceUiState.AvailableToChoose(value = "q2 choice 4"),
            actionUiState = ActionUiState.None
        )
        assertEquals(expectedUiState, actualUiState)
    }

    @Test
    fun caseNumberThree() {
        var actualUiState: UiState = viewModel.init()
        var expectedUiState: UiState = UiState.Question(
            questionUiState = QuestionUiState.Base(value = "question number 1"),
            choiceOneUiState = ChoiceUiState.AvailableToChoose(value = "q1 choice 1"),
            choiceTwoUiState = ChoiceUiState.AvailableToChoose(value = "q1 choice 2"),
            choiceThreeUiState = ChoiceUiState.AvailableToChoose(value = "q1 choice 3"),
            choiceFourUiState = ChoiceUiState.AvailableToChoose(value = "q1 choice 4"),
            actionUiState = ActionUiState.None
        )
        assertEquals(expectedUiState, actualUiState)

        actualUiState = viewModel.chooseFirst()
        expectedUiState = UiState.ChoiceMade(
            choiceOneUiState = ChoiceUiState.ChoiceMade,
            choiceTwoUiState = ChoiceUiState.Empty,
            choiceThreeUiState = ChoiceUiState.Empty,
            choiceFourUiState = ChoiceUiState.Empty,
            actionUiState = ActionUiState.Check
        )
        assertEquals(expectedUiState, actualUiState)

        actualUiState = viewModel.chooseSecond()
        expectedUiState = UiState.ChoiceMade(
            choiceOneUiState = ChoiceUiState.Empty,
            choiceTwoUiState = ChoiceUiState.ChoiceMade,
            choiceThreeUiState = ChoiceUiState.Empty,
            choiceFourUiState = ChoiceUiState.Empty,
            actionUiState = ActionUiState.Check
        )
        assertEquals(expectedUiState, actualUiState)

        actualUiState = viewModel.chooseThird()
        expectedUiState = UiState.ChoiceMade(
            choiceOneUiState = ChoiceUiState.Empty,
            choiceTwoUiState = ChoiceUiState.Empty,
            choiceThreeUiState = ChoiceUiState.ChoiceMade,
            choiceFourUiState = ChoiceUiState.Empty,
            actionUiState = ActionUiState.Check
        )
        assertEquals(expectedUiState, actualUiState)

        actualUiState = viewModel.chooseFourth()
        expectedUiState = UiState.ChoiceMade(
            choiceOneUiState = ChoiceUiState.Empty,
            choiceTwoUiState = ChoiceUiState.Empty,
            choiceThreeUiState = ChoiceUiState.Empty,
            choiceFourUiState = ChoiceUiState.ChoiceMade,
            actionUiState = ActionUiState.Check
        )
        assertEquals(expectedUiState, actualUiState)
    }
}

private class FakeRepository : Repository {

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
}