package com.example.quizgame.load.di

import com.example.quizgame.core.data.StringCache
import com.example.quizgame.core.di.Core
import com.example.quizgame.core.di.Module
import com.example.quizgame.core.di.ProvideAbstract
import com.example.quizgame.core.di.ProvideViewModel
import com.example.quizgame.load.data.CacheDataSource
import com.example.quizgame.load.data.CloudDataSource
import com.example.quizgame.load.data.LoadRepository
import com.example.quizgame.load.data.QuestionsService
import com.example.quizgame.load.data.ResponseCloud
import com.example.quizgame.load.presentation.LoadViewModel
import com.example.quizgame.main.presentation.RunAsync


class LoadModule(private val core: Core) : Module<LoadViewModel> {

    override fun viewModel(): LoadViewModel = with(core) {
        return LoadViewModel(

            LoadRepository.Base(
                lastScreen,
                CloudDataSource.Base(
                    // QuestionsService.Mock(gson)
                    QuestionsService.Base(),
                    gson
                ),
                CacheDataSource.Base(
                    StringCache.Base(
                        "gameData", core.permanentStorage, gson.toJson(ResponseCloud(emptyList()))
                    ), gson
                )
            ),

            RunAsync.Base()
        )
    }
}

class ProvideLoadViewModel(
    core: Core,
    provideOther: ProvideViewModel
) : ProvideAbstract(core, provideOther, LoadViewModel::class.java) {

    override fun module() = LoadModule(core)
}