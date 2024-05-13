package com.example.quizgame.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.quizgame.ProvideMainViewModel
import com.example.quizgame.R
import com.example.quizgame.presentation.game.GameNavigation
import com.example.quizgame.presentation.game.GameScreen
import com.example.quizgame.presentation.stats.StatsNavigation
import com.example.quizgame.presentation.stats.StatsScreen

class MainActivity : AppCompatActivity(), Navigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = (application as ProvideMainViewModel).mainViewModel()

        val lastScreen = viewModel.init(savedInstanceState == null)
        navigate(lastScreen)
    }

    override fun navigate(screen: Screen) {
        screen.show(R.id.container, supportFragmentManager)
    }
}

interface Navigation : GameNavigation, StatsNavigation {

    fun navigate(screen: Screen)

    override fun navigateFromGameScreen() {
        navigate(StatsScreen)
    }

    override fun navigateFromStats() {
        navigate(GameScreen)
    }
}

interface Screen {

    fun show(containerId: Int, fragmentManager: FragmentManager) = Unit

    object Empty : Screen

    abstract class Replace : Screen {

        abstract fun fragment(): Fragment

        override fun show(containerId: Int, fragmentManager: FragmentManager) {
            fragmentManager.beginTransaction()
                .replace(containerId, fragment())
                .commit()
        }
    }
}