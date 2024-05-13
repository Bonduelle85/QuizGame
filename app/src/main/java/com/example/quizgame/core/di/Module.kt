package com.example.quizgame.core.di

import com.example.quizgame.main.presentation.MyViewModel

interface Module<T : MyViewModel> {

    fun viewModel(): T
}