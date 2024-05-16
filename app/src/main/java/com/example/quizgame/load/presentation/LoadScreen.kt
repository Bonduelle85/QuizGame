package com.example.quizgame.load.presentation

import androidx.fragment.app.Fragment
import com.example.quizgame.main.presentation.Screen

object LoadScreen : Screen.Replace() {

    override fun fragment(): Fragment {
        return LoadFragment()
    }
}