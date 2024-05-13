package com.example.quizgame.core.di

import com.example.quizgame.game.di.ProvideGameViewModel
import com.example.quizgame.main.di.ProvideMainViewModel
import com.example.quizgame.main.presentation.MyViewModel
import com.example.quizgame.stats.di.ProvideStatsViewModel

interface ProvideViewModel {

    fun <T : MyViewModel> viewModel(clazz: Class<T>): T

    class Factory(
        private val make: ProvideViewModel
    ) : ManageViewModels {

        private val mutableMap = mutableMapOf<Class<out MyViewModel>, MyViewModel?>()

        override fun clear(clazz: Class<out MyViewModel>) {
            mutableMap[clazz] = null
        }

        override fun <T : MyViewModel> viewModel(clazz: Class<T>): T {
            return if (mutableMap[clazz] == null) {
                val viewModel = make.viewModel(clazz)
                mutableMap[clazz] = viewModel
                viewModel
            } else
                mutableMap[clazz] as T
        }
    }

    class Make(core: Core) : ProvideViewModel {

        private val chain: ProvideViewModel

        init {
            var temp: ProvideViewModel = Error()
            temp = ProvideMainViewModel(core, temp)
            temp = ProvideGameViewModel(core, temp)
            chain = ProvideStatsViewModel(core, temp)
        }

        override fun <T : MyViewModel> viewModel(clazz: Class<T>): T {
            return chain.viewModel(clazz)
        }
    }
}