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

    private lateinit var viewModel: GameViewModel
    private lateinit var showUi: (GameUiState) -> Unit

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

        val manageViewModels = activity as ManageViewModels
        viewModel = manageViewModels.viewModel(GameViewModel::class.java)

        showUi = {uiState ->
            uiState.update(
                binding.questionTextView,
                binding.choiceOneButton,
                binding.choiceTwoButton,
                binding.choiceThreeButton,
                binding.choiceFourButton,
                binding.actionButton
            )
            uiState.navigate {
                manageViewModels.clear(GameViewModel::class.java)
                (requireActivity() as GameNavigation).navigateFromGameScreen()
            }
        }

        binding.choiceOneButton.setOnClickListener {
            viewModel.chooseFirst()
        }

        binding.choiceTwoButton.setOnClickListener {
            viewModel.chooseSecond()
        }

        binding.choiceThreeButton.setOnClickListener {
            viewModel.chooseThird()
        }

        binding.choiceFourButton.setOnClickListener {
            viewModel.chooseFourth()
        }

        binding.actionButton.setOnClickListener {
            binding.actionButton.handleAction(viewModel)
        }

        viewModel.init(savedInstanceState == null)
    }

    override fun onResume() {
        super.onResume()
        viewModel.startGettingUpdates(observer = showUi)
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopGettingUpdates()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

interface GameNavigation {

    fun navigateFromGameScreen()
}