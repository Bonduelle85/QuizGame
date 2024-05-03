package com.example.quizgame

import com.example.quizgame.views.question.QuestionUiState
import com.example.quizgame.views.question.UpdateText
import org.junit.Assert.assertEquals
import org.junit.Test

class QuestionUiStateTest {

    @Test
    fun test() {
        val uiState = QuestionUiState.Base("testing string")
        val textView = FakeTextView()
        uiState.show(textView)
        assertEquals("testing string", textView.actual)
    }
}

private class FakeTextView : UpdateText {

    var actual: String = ""

    override fun update(text: String) {
        actual = text
    }

}