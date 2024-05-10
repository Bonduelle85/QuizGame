package com.example.quizgame.presentation.game

import androidx.fragment.app.Fragment
import com.example.quizgame.presentation.main.Screen

object GameScreen : Screen.Replace() {

    override fun fragment(): Fragment {
        return GameFragment()
    }
}