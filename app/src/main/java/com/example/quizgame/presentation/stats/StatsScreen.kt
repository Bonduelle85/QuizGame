package com.example.quizgame.presentation.stats

import androidx.fragment.app.Fragment
import com.example.quizgame.presentation.main.Screen

object StatsScreen : Screen.Replace() {

    override fun fragment(): Fragment {
        return StatsFragment()
    }
}