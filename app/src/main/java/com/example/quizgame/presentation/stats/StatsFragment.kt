package com.example.quizgame.presentation.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quizgame.ManageViewModels
import com.example.quizgame.R
import com.example.quizgame.databinding.FragmentStatisticsBinding

class StatsFragment : Fragment() {

    private var _binding: FragmentStatisticsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manageViewModels = activity as ManageViewModels
        val viewModel = manageViewModels.viewModel(StatsViewModel::class.java)

        val stats = viewModel.statistics()
        binding.statisticsTextView.text =
            getString(R.string.statistics_values, stats.corrects, stats.incorrects)

        binding.newGameButton.setOnClickListener {
            viewModel.clear()
            manageViewModels.clear(StatsViewModel::class.java)
            (requireActivity() as StatsNavigation).navigateFromStats()
        }
        viewModel.init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

interface StatsNavigation {
    fun navigateFromStats()
}

data class Stats(val corrects: Int, val incorrects: Int)