package com.example.quizgame

import android.app.Application
import android.content.Context
import com.example.quizgame.data.IntCache
import com.example.quizgame.data.PermanentStorage
import com.example.quizgame.data.Repository
import com.example.quizgame.presentation.MainViewModel

class QuizApp : Application() {

    lateinit var viewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()

        val permanentStorage = PermanentStorage.Base(
            getSharedPreferences(
                getString(R.string.app_name), Context.MODE_PRIVATE
            )
        )
        viewModel = MainViewModel(
            Repository.Base(
                IntCache.Base("currentIndex", permanentStorage, 0),
                IntCache.Base("userChoiceIndex", permanentStorage, -1)
            )
        )
    }
}