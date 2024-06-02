package com.example.quizgame.load.presentation

import com.example.quizgame.core.presentation.UiObservable

interface LoadUiObservable : UiObservable<LoadUiState> {
    class Base : UiObservable.Abstract<LoadUiState>(), LoadUiObservable
}
