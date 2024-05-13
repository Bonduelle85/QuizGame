package com.example.quizgame.stats.presentation

import androidx.fragment.app.Fragment
import com.example.quizgame.main.presentation.Screen

object StatsScreen : Screen.Replace() {

    override fun fragment(): Fragment {
        return StatsFragment()
    }
}