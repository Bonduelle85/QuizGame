package com.example.quizgame

import com.example.quizgame.views.choice.ChoiceButtonAction
import com.example.quizgame.views.choice.ChoiceUiState
import org.junit.Assert
import org.junit.Test

class ChoiceGameUiStateTest {

    @Test
    fun testAvailableToChoose() {
        val availableToChoose = ChoiceUiState.AvailableToChoose("text")
        val button = FakeChoiceButtonAction()
        availableToChoose.show(button)

        Assert.assertEquals("text", button.actualText)
        Assert.assertEquals(R.color.yellow, button.actualBackgroundColor)
        Assert.assertEquals(true, button.actualIsClickable)
    }

    @Test
    fun testChoiceMade() {
        val choiceMade = ChoiceUiState.ChoiceMade
        val button = FakeChoiceButtonAction()
        choiceMade.show(button)

        Assert.assertEquals("", button.actualText)
        Assert.assertEquals(R.color.purple, button.actualBackgroundColor)
        Assert.assertEquals(false, button.actualIsClickable)
    }

    @Test
    fun testIncorrect() {
        val incorrect = ChoiceUiState.Incorrect
        val button = FakeChoiceButtonAction()
        incorrect.show(button)

        Assert.assertEquals("", button.actualText)
        Assert.assertEquals(R.color.red, button.actualBackgroundColor)
        Assert.assertEquals(null, button.actualIsClickable)
    }
}

private class FakeChoiceButtonAction: ChoiceButtonAction {

    var actualText: String = ""
    var actualBackgroundColor: Int = -1
    var actualIsClickable: Boolean? = null

    override fun updateUiState(outer: ChoiceUiState) {
        //todo SOLID : I interface segregation
    }

    override fun updateText(text: String) {
        actualText = text
    }

    override fun updateUi(colorResId: Int, clickable: Boolean) {
        actualBackgroundColor = colorResId
        actualIsClickable = clickable
    }
}