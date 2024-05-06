package com.example.quizgame.views.question


interface QuestionUiState {
    fun show(textView: UpdateQuestionText)

    data class Base(private val value: String) : QuestionUiState {
        override fun show(textView: UpdateQuestionText) {
            textView.update(value)
        }
    }
}

interface UpdateQuestionText {

    fun update(text: String)
}