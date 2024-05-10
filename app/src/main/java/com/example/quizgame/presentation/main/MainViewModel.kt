package com.example.quizgame.presentation.main

import com.example.quizgame.data.MainRepository

class MainViewModel(
    private val mainRepository: MainRepository
) {

    fun init(firstRun: Boolean): Screen {
        return if (firstRun)
            mainRepository.lastSavedScreen()
        else
            Screen.Empty
    }
}