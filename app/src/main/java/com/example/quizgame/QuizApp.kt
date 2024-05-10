package com.example.quizgame

import android.app.Application
import android.content.Context
import com.example.quizgame.data.BooleanCache
import com.example.quizgame.data.IntCache
import com.example.quizgame.data.MainRepository
import com.example.quizgame.data.PermanentStorage
import com.example.quizgame.data.Repository
import com.example.quizgame.presentation.game.GameViewModel
import com.example.quizgame.presentation.main.MainViewModel
import com.example.quizgame.presentation.stats.StatsRepository
import com.example.quizgame.presentation.stats.StatsViewModel

class QuizApp : Application() {

    lateinit var mainViewModel: MainViewModel
    lateinit var gameViewModel: GameViewModel
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

        val isLastScreenGame = BooleanCache.Base("isLastScreenGame", permanentStorage, true)


        gameViewModel = GameViewModel(
            Repository.Base(
                isLastScreenGame,
                corrects,
                incorrects,
                IntCache.Base("currentIndex", permanentStorage, 0),
                IntCache.Base("userChoiceIndex", permanentStorage, -1)
            )
        )
        statsViewModel =
            StatsViewModel(StatsRepository.Base(isLastScreenGame, corrects, incorrects))

        mainViewModel = MainViewModel(MainRepository.Base(isLastScreenGame))
    }
}