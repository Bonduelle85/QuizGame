package com.example.quizgame.presentation.main

import com.example.quizgame.data.MainRepository

class MainViewModel(
    private val mainRepository: MainRepository
) {

    fun init(firstRun: Boolean): Boolean? {
        return if (firstRun) {
            mainRepository.lastScreenIsGame()
        } else null
    }
}