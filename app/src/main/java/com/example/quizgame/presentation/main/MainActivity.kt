package com.example.quizgame.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.quizgame.QuizApp
import com.example.quizgame.R
import com.example.quizgame.presentation.game.GameFragment
import com.example.quizgame.presentation.stats.StatsFragment

class MainActivity : AppCompatActivity(), Navigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = (application as QuizApp).mainViewModel

        val isLastScreenGame = viewModel.init(savedInstanceState == null)
        isLastScreenGame?.let {
            navigate(
                if (it)
                    GameFragment()
                else
                    StatsFragment()
            )
        }
    }

    override fun navigate(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}

interface Navigation {
    fun navigate(fragment: Fragment)
}