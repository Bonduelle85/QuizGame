package com.example.quizgame.stats.di

import com.example.quizgame.core.di.Core
import com.example.quizgame.core.di.Module
import com.example.quizgame.core.di.ProvideAbstract
import com.example.quizgame.core.di.ProvideViewModel
import com.example.quizgame.stats.data.StatsRepository
import com.example.quizgame.stats.presentation.StatsViewModel

class StatsModule(private val core: Core) : Module<StatsViewModel> {

    override fun viewModel(): StatsViewModel = with(core) {
        return StatsViewModel(
            StatsRepository.Base(
                lastScreen,
                corrects,
                incorrects
            )
        )
    }
}

class ProvideStatsViewModel(
    core: Core,
    provideOther: ProvideViewModel
) : ProvideAbstract(core, provideOther, StatsViewModel::class.java) {

    override fun module() = StatsModule(core)
}