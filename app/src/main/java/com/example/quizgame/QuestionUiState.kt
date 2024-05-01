package com.example.quizgame


interface QuestionUiState {
    fun show(textView: UpdateText)

    data class Base(private val value: String) : QuestionUiState {
        override fun show(textView: UpdateText) {
            textView.show(value)
        }
    }
}

interface UpdateText {

    fun show(text: String)
}