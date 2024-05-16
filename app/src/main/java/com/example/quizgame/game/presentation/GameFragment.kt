package com.example.quizgame.game.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quizgame.core.di.ManageViewModels
import com.example.quizgame.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lateinit var uiState: GameUiState

        val manageViewModels = activity as ManageViewModels
        val viewModel = manageViewModels.viewModel(GameViewModel::class.java)

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
            uiState.navigate {
                manageViewModels.clear(GameViewModel::class.java)
                (requireActivity() as GameNavigation).navigateFromGameScreen()
            }
        }

        uiState = viewModel.init(savedInstanceState == null)
        showUi.invoke()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

interface GameNavigation {

    fun navigateFromGameScreen()
}