package com.example.quizgame

interface QuestionUiState {

    data class Base(private val value: String) : QuestionUiState {

    }
}