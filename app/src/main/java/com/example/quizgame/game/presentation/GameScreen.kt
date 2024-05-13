package com.example.quizgame.game.presentation

import androidx.fragment.app.Fragment
import com.example.quizgame.main.presentation.Screen

object GameScreen : Screen.Replace() {

    override fun fragment(): Fragment {
        return GameFragment()
    }
}