package com.example.quizgame.main.di

import com.example.quizgame.core.di.Core
import com.example.quizgame.core.di.Module
import com.example.quizgame.core.di.ProvideAbstract
import com.example.quizgame.core.di.ProvideViewModel
import com.example.quizgame.main.data.MainRepository
import com.example.quizgame.main.presentation.MainViewModel

class MainModule(private val core: Core) : Module<MainViewModel> {

    override fun viewModel(): MainViewModel {
        return MainViewModel(MainRepository.Base(core.lastScreen))
    }
}

class ProvideMainViewModel(
    core: Core,
    provideOther: ProvideViewModel
) : ProvideAbstract(core, provideOther, MainViewModel::class.java) {

    override fun module() = MainModule(core)
}