package com.example.quizgame

import android.app.Application
import android.content.Context
import com.example.quizgame.data.MainRepository
import com.example.quizgame.data.Repository
import com.example.quizgame.data.core.IntCache
import com.example.quizgame.data.core.PermanentStorage
import com.example.quizgame.data.core.StringCache
import com.example.quizgame.presentation.game.GameScreen
import com.example.quizgame.presentation.game.GameViewModel
import com.example.quizgame.presentation.main.MainViewModel
import com.example.quizgame.presentation.stats.StatsRepository
import com.example.quizgame.presentation.stats.StatsViewModel

class QuizApp : Application(), ProvideViewModels {

    private var mainViewModel: MainViewModel? = null
    private var gameViewModel: GameViewModel? = null
    private var statsViewModel: StatsViewModel? = null

    private lateinit var lastScreen: StringCache
    private lateinit var corrects: IntCache
    private lateinit var incorrects: IntCache
    private lateinit var permanentStorage: PermanentStorage

    override fun onCreate() {
        super.onCreate()
        val name = getString(R.string.app_name)
        permanentStorage = PermanentStorage.Base(
            getSharedPreferences(
                name, Context.MODE_PRIVATE
            )
        )
        corrects = IntCache.Base("corrects", permanentStorage, 0)
        incorrects = IntCache.Base("incorrects", permanentStorage, 0)

        lastScreen =
            StringCache.Base("lastScreen", permanentStorage, GameScreen::class.java.canonicalName)
    }

    override fun gameViewModel(): GameViewModel {
        if (gameViewModel == null) {
            gameViewModel = GameViewModel(
                Repository.Base(
                    lastScreen,
                    corrects,
                    incorrects,
                    IntCache.Base("currentIndex", permanentStorage, 0),
                    IntCache.Base("userChoiceIndex", permanentStorage, -1)
                )
            )
        }
        return gameViewModel!!
    }

    override fun clearGameViewModel() {
        gameViewModel = null
    }

    override fun mainViewModel(): MainViewModel {
        if (mainViewModel == null) {
            mainViewModel = MainViewModel(
                MainRepository.Base(
                    lastScreen,
                )
            )
        }
        return mainViewModel!!
    }

    override fun statsViewModel(): StatsViewModel {
        if (statsViewModel == null) {
            statsViewModel = StatsViewModel(
                StatsRepository.Base(
                    lastScreen,
                    corrects,
                    incorrects
                )
            )
        }
        return statsViewModel!!
    }

    override fun clearStatsViewModel() {
        statsViewModel = null
    }
}

interface ProvideMainViewModel {
    fun mainViewModel(): MainViewModel
}

interface ProvideStatsViewModel {
    fun statsViewModel(): StatsViewModel
    fun clearStatsViewModel()
}

interface ProvideGameViewModel {
    fun gameViewModel(): GameViewModel
    fun clearGameViewModel()
}

interface ProvideViewModels : ProvideGameViewModel, ProvideMainViewModel, ProvideStatsViewModel