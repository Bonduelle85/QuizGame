package com.example.quizgame

import android.app.Application

class QuizApp : Application() {

    lateinit var viewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()

        viewModel = MainViewModel(Repository.Base())
    }
}