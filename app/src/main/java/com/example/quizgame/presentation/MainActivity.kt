package com.example.quizgame.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizgame.QuizApp
import com.example.quizgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lateinit var uiState: UiState
        val viewModel = (application as QuizApp).viewModel

        val showUi: () -> Unit = {
            uiState.update(
                binding.questionTextView,
                binding.choiceOneButton,
                binding.choiceTwoButton,
                binding.choiceThreeButton,
                binding.choiceFourButton,
                binding.actionButton
            )
        }

        binding.choiceOneButton.setOnClickListener {
            uiState = viewModel.chooseFirst()
            showUi.invoke()
        }

        binding.choiceTwoButton.setOnClickListener {
            uiState = viewModel.chooseSecond()
            showUi.invoke()
        }

        binding.choiceThreeButton.setOnClickListener {
            uiState = viewModel.chooseThird()
            showUi.invoke()
        }

        binding.choiceFourButton.setOnClickListener {
            uiState = viewModel.chooseFourth()
            showUi.invoke()
        }

        binding.actionButton.setOnClickListener {
            uiState = binding.actionButton.handleAction(viewModel)
            showUi.invoke()
        }

        uiState = viewModel.init(savedInstanceState == null)
        showUi.invoke()
    }
}