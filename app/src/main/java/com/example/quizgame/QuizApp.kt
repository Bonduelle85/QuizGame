package com.example.quizgame

import android.app.Application
import android.content.Context
import com.example.quizgame.data.IntCache
import com.example.quizgame.data.PermanentStorage
import com.example.quizgame.data.Repository
import com.example.quizgame.presentation.game.GameViewModel
import com.example.quizgame.presentation.stats.StatsRepository
import com.example.quizgame.presentation.stats.StatsViewModel

class QuizApp : Application() {

    lateinit var gamrViewModel: GameViewModel
    lateinit var statsViewModel: StatsViewModel

    override fun onCreate() {
        super.onCreate()

        val permanentStorage = PermanentStorage.Base(
            getSharedPreferences(
                getString(R.string.app_name), Context.MODE_PRIVATE
            )
        )
        val corrects = IntCache.Base("corrects", permanentStorage, 0)
        val incorrects = IntCache.Base("incorrects", permanentStorage, 0)
        gamrViewModel = GameViewModel(
            Repository.Base(
                corrects,
                incorrects,
                IntCache.Base("currentIndex", permanentStorage, 0),
                IntCache.Base("userChoiceIndex", permanentStorage, -1)
            )
        )
        statsViewModel = StatsViewModel(StatsRepository.Base(corrects, incorrects))
    }
}