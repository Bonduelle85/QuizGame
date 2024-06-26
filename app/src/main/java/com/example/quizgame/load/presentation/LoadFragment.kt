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

    private lateinit var viewModel: LoadViewModel
    private lateinit var showUi: (LoadUiState) -> Unit

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

        val manageViewModels = activity as ManageViewModels
        viewModel = manageViewModels.viewModel(LoadViewModel::class.java)

        val exit = {
            manageViewModels.clear(LoadViewModel::class.java)
            (activity as LoadNavigation).navigateFromLoad()
        }

        showUi = { uiState ->
            uiState.update(
                progress = binding.progressBar,
                error = binding.errorTextView,
                retry = binding.retryButton
            )
            uiState.navigate(exit)
        }

        binding.retryButton.setOnClickListener {
            viewModel.retry()
        }

        viewModel.init(savedInstanceState == null)
    }

    override fun onResume() {
        super.onResume()
        viewModel.startGettingUpdates(showUi)
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

interface LoadNavigation {

    fun navigateFromLoad()
}