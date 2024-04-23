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
        var actualUiState : UiState = viewModel.init()
        var expectedUiState : UiState = UiState.Question(
            questionUiState = QuestionUiState.Base(value = "question number 1"),
            choiceOneUiState = ChoiceUiState.AvailableToChoose(value = "q1 choice 1"),
            choiceTwoUiState = ChoiceUiState.AvailableToChoose(value = "q2 choice 2"),
            choiceThreeUiState = ChoiceUiState.AvailableToChoose(value = "q3 choice 3"),
            choiceFourUiState = ChoiceUiState.AvailableToChoose(value = "q4 choice 4"),
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
        var actualUiState : UiState = viewModel.init()
        var expectedUiState : UiState = UiState.Question(
            questionUiState = QuestionUiState.Base(value = "question number 1"),
            choiceOneUiState = ChoiceUiState.AvailableToChoose(value = "choice 1"),
            choiceTwoUiState = ChoiceUiState.AvailableToChoose(value = "choice 2"),
            choiceThreeUiState = ChoiceUiState.AvailableToChoose(value = "choice 3"),
            choiceFourUiState = ChoiceUiState.AvailableToChoose(value = "choice 4"),
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
        var actualUiState : UiState = viewModel.init()
        var expectedUiState : UiState = UiState.Question(
            questionUiState = QuestionUiState.Base(value = "question number 1"),
            choiceOneUiState = ChoiceUiState.AvailableToChoose(value = "choice 1"),
            choiceTwoUiState = ChoiceUiState.AvailableToChoose(value = "choice 2"),
            choiceThreeUiState = ChoiceUiState.AvailableToChoose(value = "choice 3"),
            choiceFourUiState = ChoiceUiState.AvailableToChoose(value = "choice 4"),
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