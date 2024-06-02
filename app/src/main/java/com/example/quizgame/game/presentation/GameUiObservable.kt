package com.example.quizgame.game.presentation

import com.example.quizgame.core.presentation.UiObservable


interface GameUiObservable : UiObservable<GameUiState> {
    class Base : UiObservable.Abstract<GameUiState>(), GameUiObservable
}