package com.example.quizgame.main.data

import com.example.quizgame.core.data.StringCache
import com.example.quizgame.main.presentation.Screen

interface MainRepository {

    fun lastSavedScreen(): Screen

    class Base(
        private val lastScreen: StringCache,
    ) : MainRepository {

        override fun lastSavedScreen(): Screen {
            val string = lastScreen.read()
            return Class.forName(string).getDeclaredConstructor().newInstance() as Screen
        }
    }
}