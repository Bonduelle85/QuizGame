package com.example.quizgame.core.di

import com.example.quizgame.main.presentation.MyViewModel

interface ClearViewModel {

    fun clear(clazz: Class<out MyViewModel>)
}