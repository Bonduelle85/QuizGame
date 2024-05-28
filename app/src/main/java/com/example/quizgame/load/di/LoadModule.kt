package com.example.quizgame.load.di

import com.example.quizgame.core.data.StringCache
import com.example.quizgame.core.di.Core
import com.example.quizgame.core.di.Module
import com.example.quizgame.core.di.ProvideAbstract
import com.example.quizgame.core.di.ProvideViewModel
import com.example.quizgame.load.data.CacheDataSource
import com.example.quizgame.load.data.CloudDataSource
import com.example.quizgame.load.data.FakeService
import com.example.quizgame.load.data.LoadRepository
import com.example.quizgame.load.data.QuestionService
import com.example.quizgame.load.data.ResponseCloud
import com.example.quizgame.load.presentation.LoadViewModel
import com.example.quizgame.load.presentation.UiObservable
import com.example.quizgame.main.presentation.RunAsync
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoadModule(private val core: Core) : Module<LoadViewModel> {

    override fun viewModel(): LoadViewModel = with(core) {
        return LoadViewModel(
            UiObservable.Base(),
            LoadRepository.Base(
                lastScreen,
                CloudDataSource.Base(
                    if (core.runUiTest)
                        FakeService()
                    else
                        Retrofit.Builder().baseUrl("https://opentdb.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(
                                OkHttpClient.Builder()
                                    .retryOnConnectionFailure(true)
                                    .addInterceptor(HttpLoggingInterceptor().apply {
                                        setLevel(HttpLoggingInterceptor.Level.BODY)
                                    })
                                    .build()
                            )
                            .build()
                            .create(QuestionService::class.java)
                ),
                CacheDataSource.Base(
                    StringCache.Base(
                        "gameData", core.permanentStorage, gson.toJson(ResponseCloud(emptyList()))
                    ), gson
                )
            ),RunAsync.Base()
        )
    }
}

class ProvideLoadViewModel(
    core: Core,
    provideOther: ProvideViewModel
) : ProvideAbstract(core, provideOther, LoadViewModel::class.java) {

    override fun module() = LoadModule(core)
}