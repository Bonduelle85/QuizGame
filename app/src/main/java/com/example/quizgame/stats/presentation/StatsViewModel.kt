package com.example.quizgame.stats.presentation

import com.example.quizgame.main.presentation.MyViewModel
import com.example.quizgame.stats.data.StatsRepository

class StatsViewModel(
    private val repository: StatsRepository
) : MyViewModel {

    fun init() {
        repository.saveLastScreenIsStats()
    }

    fun statistics(): Stats {
        return Stats(repository.corrects(), repository.incorrects())
    }

    fun clear() {
        repository.clear()
    }
}

