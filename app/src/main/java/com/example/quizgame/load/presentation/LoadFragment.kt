package com.example.quizgame.load.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quizgame.core.di.ManageViewModels
import com.example.quizgame.databinding.FragmentLoadBinding


class LoadFragment : Fragment() {

    private var _binding: FragmentLoadBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lateinit var uiState: LoadUiState

        val manageViewModels = activity as ManageViewModels
        val viewModel = manageViewModels.viewModel(LoadViewModel::class.java)

        val exit = {
            manageViewModels.clear(LoadViewModel::class.java)
            (activity as LoadNavigation).navigateFromLoad()
        }

        val showUi: () -> Unit = {
            uiState.update(
                progress = binding.progressBar,
                error = binding.errorTextView,
                retry = binding.retryButton
            )
            uiState.navigate(exit)
        }

        binding.retryButton.setOnClickListener {
            uiState = viewModel.retry()
            showUi.invoke()
        }

        uiState = viewModel.init(savedInstanceState == null)
        showUi.invoke()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

interface LoadNavigation {

    fun navigateFromLoad()
}