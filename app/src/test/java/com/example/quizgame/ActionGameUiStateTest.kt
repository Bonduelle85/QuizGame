package com.example.quizgame

import android.view.View
import com.example.quizgame.core.views.action.ActionUiState
import com.example.quizgame.core.views.action.UpdateActionButton
import com.example.quizgame.core.views.choice.ChoiceButtonAction
import com.example.quizgame.core.views.question.UpdateText
import com.example.quizgame.game.presentation.Actions
import com.example.quizgame.game.presentation.GameUiState
import org.junit.Assert.assertEquals
import org.junit.Test

class ActionGameUiStateTest {

    @Test
    fun testNoneUiState() {
        val none = ActionUiState.None
        val button = FakeButton()
        none.show(button)

        assertEquals(R.string.check, button.actualTextId)
        assertEquals(View.INVISIBLE, button.actualVisibility)
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
        val expected = FakeGameUiState("1")
        assertEquals(expected, actual)
    }

    @Test
    fun testNext() {
        val check = ActionUiState.Next
        val viewModel = FakeViewModel()
        val actual = check.handleAction(viewModel)
        val expected = FakeGameUiState("2")
        assertEquals(expected, actual)
    }
}

private class FakeViewModel : Actions {

    override fun check(): GameUiState {
        return FakeGameUiState("1")
    }

    override fun next(): GameUiState {
        return FakeGameUiState("2")
    }
}

private data class FakeGameUiState(private val id: String) : GameUiState {

    override fun update(
        questionTextView: UpdateText,
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