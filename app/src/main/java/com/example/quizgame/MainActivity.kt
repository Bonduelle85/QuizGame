package com.example.quizgame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = (application as QuizApp).viewModel

        binding.choiceOneButton.setOnClickListener {
            val uiState: UiState = viewModel.chooseFirst()
            uiState.update(binding)
        }

        binding.choiceTwoButton.setOnClickListener {
            val uiState: UiState = viewModel.chooseSecond()
            uiState.update(binding)
        }

        binding.choiceThreeButton.setOnClickListener {
            val uiState: UiState = viewModel.chooseThird()
            uiState.update(binding)
        }

        binding.choiceFourButton.setOnClickListener {
            val uiState: UiState = viewModel.chooseFourth()
            uiState.update(binding)
        }

        binding.actionButton.setOnClickListener {
            val uiState: UiState = viewModel.handleAction()
            uiState.update(binding)
        }

        val uiState: UiState = viewModel.init()
        uiState.update(binding)
    }
}