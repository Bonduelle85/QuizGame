package com.example.quizgame.game.di

import com.example.quizgame.core.data.IntCache
import com.example.quizgame.core.data.StringCache
import com.example.quizgame.core.di.Core
import com.example.quizgame.core.di.Module
import com.example.quizgame.core.di.ProvideAbstract
import com.example.quizgame.core.di.ProvideViewModel
import com.example.quizgame.game.data.GameRepository
import com.example.quizgame.game.presentation.GameViewModel
import com.example.quizgame.load.data.CacheDataSource
import com.example.quizgame.load.data.ResponseCloud

class GameModule(private val core: Core) : Module<GameViewModel> {

    override fun viewModel(): GameViewModel = with(core) {
        return GameViewModel(
            GameRepository.Base(
                lastScreen,
                corrects,
                incorrects,
                IntCache.Base("currentIndex", permanentStorage, 0),
                IntCache.Base("userChoiceIndex", permanentStorage, -1),
                CacheDataSource.Base(
                    StringCache.Base(
                        "gameData", core.permanentStorage, gson.toJson(ResponseCloud(emptyList()))
                    ), gson
                )
            )
        )
    }
}

class ProvideGameViewModel(
    core: Core,
    provideOther: ProvideViewModel
) : ProvideAbstract(core, provideOther, GameViewModel::class.java) {

    override fun module() = GameModule(core)
}