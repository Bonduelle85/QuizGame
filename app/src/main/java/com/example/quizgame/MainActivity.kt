package com.example.quizgame

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var uiState: UiState
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            uiState = viewModel.handleAction()
            uiState.update(binding)
        }

        uiState = if (savedInstanceState == null) {
            viewModel.init()
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                savedInstanceState.getSerializable(KEY, UiState::class.java) as UiState
            } else {
                savedInstanceState.getSerializable(KEY) as UiState
            }
        }
        uiState.update(binding)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, uiState)
    }

    companion object {
        private const val KEY = "uiStateKey"
    }
}