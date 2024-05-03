package com.example.quizgame

import android.view.View
import com.example.quizgame.presentation.Actions
import com.example.quizgame.presentation.UiState
import com.example.quizgame.views.action.ActionUiState
import com.example.quizgame.views.action.UpdateActionButton
import com.example.quizgame.views.choice.ChoiceButtonAction
import com.example.quizgame.views.question.UpdateText
import org.junit.Assert.assertEquals
import org.junit.Test

class ActionUiStateTest {

    @Test
    fun testNoneUiState() {
        val none = ActionUiState.None
        val button = FakeButton()
        none.show(button)

        assertEquals(R.string.check, button.actualTextId)
        assertEquals(View.INVISIBLE, button.actualVisibility)
    }

    @Test
    fun testCheckUiState() {
        val check = ActionUiState.Check
        val button = FakeButton()
        check.show(button)

        assertEquals(R.string.check, button.actualTextId)
        assertEquals(View.VISIBLE, button.actualVisibility)
    }

    @Test
    fun testNextUiState() {
        val next = ActionUiState.Next
        val button = FakeButton()
        next.show(button)

        assertEquals(R.string.next, button.actualTextId)
        assertEquals(View.VISIBLE, button.actualVisibility)
    }

    @Test(expected = IllegalStateException::class)
    fun testNoneAction() {
        val none = ActionUiState.None
        none.handleAction(FakeViewModel())
    }

    @Test
    fun testCheck() {
        val check = ActionUiState.Check
        val viewModel = FakeViewModel()
        val actual = check.handleAction(viewModel)
        val expected = FakeUiState("1")
        assertEquals(expected, actual)
    }

    @Test
    fun testNext() {
        val check = ActionUiState.Next
        val viewModel = FakeViewModel()
        val actual = check.handleAction(viewModel)
        val expected = FakeUiState("2")
        assertEquals(expected, actual)
    }
}

private class FakeViewModel : Actions {

    override fun check(): UiState {
        return FakeUiState("1")
    }

    override fun next(): UiState {
        return FakeUiState("2")
    }
}

private data class FakeUiState(private val id: String) : UiState {

    override fun update(
        updateText: UpdateText,
        choiceButtonOne: ChoiceButtonAction,
        choiceButtonTwo: ChoiceButtonAction,
        choiceButtonThree: ChoiceButtonAction,
        choiceButtonFour: ChoiceButtonAction,
        actionButton: UpdateActionButton
    ) = Unit

}

private class FakeButton : UpdateActionButton {

    var actualTextId: Int = 0
    var actualVisibility: Int = -1

    override fun updateUiState(outer: ActionUiState) {
        //todo SOLID : I interface segregation
    }

    override fun updateUi(textId: Int, visibility: Int) {
        actualTextId = textId
        actualVisibility = visibility
    }

}