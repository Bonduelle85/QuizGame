package com.example.quizgame

import android.widget.TextView
interface QuestionUiState {
    fun show(textView: TextView)

    data class Base(private val value: String) : QuestionUiState {
        override fun show(textView: TextView) {
            textView.text = value
        }
    }
}