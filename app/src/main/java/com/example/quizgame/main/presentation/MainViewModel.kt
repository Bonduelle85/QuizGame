package com.example.quizgame.main.presentation

import com.example.quizgame.main.data.MainRepository

class MainViewModel(
    private val mainRepository: MainRepository
) : MyViewModel {

    fun init(firstRun: Boolean): Screen {
        return if (firstRun)
            mainRepository.lastSavedScreen()
        else
            Screen.Empty
    }
}

interface MyViewModel