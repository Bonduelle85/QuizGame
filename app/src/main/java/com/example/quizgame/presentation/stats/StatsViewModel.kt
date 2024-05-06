package com.example.quizgame.presentation.stats

import com.example.quizgame.data.IntCache

class StatsViewModel(
    private val repository: StatsRepository
) {

    fun statistics(): Stats {
        val stats = Stats (repository.corrects(), repository.incorrects())
        repository.clear()
        return stats
    }
}

interface StatsRepository {

    fun corrects(): Int
    fun incorrects(): Int

    fun clear()

    class Base(
        private val corrects: IntCache,
        private val incorrects: IntCache
    ) : StatsRepository {

        override fun corrects(): Int = corrects.read()

        override fun incorrects(): Int = incorrects.read()

        override fun clear() {
            corrects.save(0)
            incorrects.save(0)
        }
    }
}

data class Stats(val corrects: Int, val incorrects: Int)