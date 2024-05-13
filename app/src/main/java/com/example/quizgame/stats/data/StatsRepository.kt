package com.example.quizgame.stats.data

import com.example.quizgame.core.data.IntCache
import com.example.quizgame.core.data.StringCache
import com.example.quizgame.stats.presentation.StatsScreen

interface StatsRepository {

    fun corrects(): Int
    fun incorrects(): Int

    fun clear()
    fun saveLastScreenIsStats()

    class Base(
        private val lastScreen: StringCache,
        private val corrects: IntCache,
        private val incorrects: IntCache
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