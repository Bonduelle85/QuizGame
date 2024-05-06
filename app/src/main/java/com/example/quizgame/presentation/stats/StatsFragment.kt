package com.example.quizgame.presentation.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quizgame.QuizApp
import com.example.quizgame.R
import com.example.quizgame.databinding.FragmentStatisticsBinding
import com.example.quizgame.presentation.game.GameFragment
import com.example.quizgame.presentation.main.Navigation

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

        val viewModel = (requireActivity().application as QuizApp).statsViewModel

        if (savedInstanceState == null) {
            val stats = viewModel.statistics()
            binding.statisticsTextView.text =
                getString(R.string.statistics_values, stats.corrects, stats.incorrects)
        }

        binding.newGameButton.setOnClickListener {
            (requireActivity() as Navigation).navigate(GameFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}