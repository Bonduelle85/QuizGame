package com.example.quizgame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lateinit var uiState: UiState
        val viewModel = (application as QuizApp).viewModel

        binding.choiceOneButton.setOnClickListener {
            uiState = viewModel.chooseFirst()
            uiState.update(binding)
        }

        binding.choiceTwoButton.setOnClickListener {
            uiState = viewModel.chooseSecond()
            uiState.update(binding)
        }

        binding.choiceThreeButton.setOnClickListener {
            uiState = viewModel.chooseThird()
            uiState.update(binding)
        }

        binding.choiceFourButton.setOnClickListener {
            uiState = viewModel.chooseFourth()
            uiState.update(binding)
        }

        binding.actionButton.setOnClickListener {
            uiState = binding.actionButton.handleAction(viewModel)
            uiState.update(binding)
        }

        if (savedInstanceState == null)
            uiState = viewModel.init().also {
                it.update(binding)
            }
    }
}