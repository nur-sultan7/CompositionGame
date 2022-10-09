package com.example.compositiongame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.compositiongame.R
import com.example.compositiongame.databinding.FragmentGameBinding
import com.example.compositiongame.domain.entiteis.GameResult
import com.example.compositiongame.domain.entiteis.GameSettings
import com.example.compositiongame.domain.entiteis.Level


class GameFragment : Fragment() {

    private lateinit var level: Level
    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArguments()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.questionTvOption1.setOnClickListener {
            launchGameFinishedFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchGameFinishedFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(
                R.id.main_container, GameFinishedFragment.newInstance(
                    GameResult(
                        true,
                        5,
                        7,
                        GameSettings(
                            10,
                            77,
                            22,
                            22
                        )
                    )
                )
            )
            .addToBackStack(null)
            .commit()
    }

    private fun parseArguments() {
        level = requireArguments().getSerializable(KEY_LEVEL) as Level
    }

    companion object {
        const val NAME = "GameFragment"
        private const val KEY_LEVEL = "level"

        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_LEVEL, level)
                }
            }
        }
    }
}