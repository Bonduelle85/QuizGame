package com.example.quizgame.main.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.quizgame.R
import com.example.quizgame.core.di.ManageViewModels
import com.example.quizgame.game.presentation.GameNavigation
import com.example.quizgame.game.presentation.GameScreen
import com.example.quizgame.stats.presentation.StatsNavigation
import com.example.quizgame.stats.presentation.StatsScreen

class MainActivity : AppCompatActivity(), Navigation, ManageViewModels {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = viewModel(MainViewModel::class.java)

        val lastScreen = viewModel.init(savedInstanceState == null)
        navigate(lastScreen)
    }

    override fun navigate(screen: Screen) {
        screen.show(R.id.container, supportFragmentManager)
    }

    override fun clear(clazz: Class<out MyViewModel>) {
        (application as ManageViewModels).clear(clazz)
    }

    override fun <T : MyViewModel> viewModel(clazz: Class<T>): T {
        return (application as ManageViewModels).viewModel(clazz)
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