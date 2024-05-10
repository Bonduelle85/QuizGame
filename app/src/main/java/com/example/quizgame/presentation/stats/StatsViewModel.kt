package com.example.quizgame.presentation.stats

import com.example.quizgame.data.core.IntCache
import com.example.quizgame.data.core.StringCache

class StatsViewModel(
    private val repository: StatsRepository
) {
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

interface StatsRepository {

    fun corrects(): Int
    fun incorrects(): Int

    fun clear()
    fun saveLastScreenIsStats()

    class Base(
        private val lastScreen: StringCache,
        private val corrects: IntCache,
        private val incorrects: IntCache,
    ) : StatsRepository {

        override fun corrects(): Int = corrects.read()

        override fun incorrects(): Int = incorrects.read()

        override fun clear() {
            corrects.save(0)
            incorrects.save(0)
        }

        override fun saveLastScreenIsStats() {
            lastScreen.save(StatsScreen::class.java.canonicalName)
        }
    }
}

data class Stats(val corrects: Int, val incorrects: Int)