package com.example.quizgame.core.views.question


interface QuestionUiState {
    fun show(textView: UpdateText)

    data class Base(private val value: String) : QuestionUiState {
        override fun show(textView: UpdateText) {
            textView.update(value)
        }
    }
}

interface UpdateText {

    fun update(text: String)
}