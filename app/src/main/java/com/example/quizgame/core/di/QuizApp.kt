package com.example.quizgame.core.di

import android.app.Application
import com.example.quizgame.main.presentation.MyViewModel

class QuizApp : Application(), ManageViewModels {

    private lateinit var factory: ManageViewModels

    override fun onCreate() {
        super.onCreate()
        factory = ProvideViewModel.Factory(ProvideViewModel.Make(Core(this)))
    }

    override fun clear(clazz: Class<out MyViewModel>) {
        factory.clear(clazz)
    }

    override fun <T : MyViewModel> viewModel(clazz: Class<T>): T {
        return factory.viewModel(clazz)
    }
}

