package com.example.compositiongame.presentation

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.compositiongame.R
import com.example.compositiongame.databinding.FragmentGameBinding
import com.example.compositiongame.domain.entiteis.GameResult
import com.example.compositiongame.domain.entiteis.Level


class GameFragment : Fragment() {
    private val gameViewModelFactory by lazy {
        GameViewModelFactory(level, requireActivity().application)
    }
    private lateinit var level: Level
    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding is null")
    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(
            this, gameViewModelFactory
        )[GameViewModel::class.java]
    }
    private val tvOptionsList: MutableList<TextView> by lazy {
        mutableListOf<TextView>().apply {
            with(binding) {
                add(questionTvOption1)
                add(questionTvOption2)
                add(questionTvOption3)
                add(questionTvOption4)
                add(questionTvOption5)
                add(questionTvOption6)
            }
        }
    }

    @RequiresApi(33)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArguments()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModelObservers()
        setOnClickListeners()

    }

    private fun setViewModelObservers() {
        with(binding) {
            with(viewModel) {
                timerTime.observe(viewLifecycleOwner) {
                    tvTimer.text = it
                }
                resultOfAnswers.observe(viewLifecycleOwner) {
                    tvCountRightAnswers.text = it
                }
                percentOfRightAnswers.observe(viewLifecycleOwner) {
                    pbQuestion.setProgress(it, true)
                }
                minPercent.observe(viewLifecycleOwner) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        pbQuestion.secondaryProgress = it
                    }
                }
                question.observe(viewLifecycleOwner) {
                    tvVisibleNumber.text = it.visibleNumber.toString()
                    tvQuestionSum.text = it.sum.toString()
                    for (optionIndex in 0 until tvOptionsList.size) tvOptionsList[optionIndex].text =
                        it.options[optionIndex].toString()
                }
                enoughRightAnswers.observe(viewLifecycleOwner) {
                    if (it) tvCountRightAnswers.setTextColor(requireContext().getColor(R.color.green))
                    else tvCountRightAnswers.setTextColor(requireContext().getColor(R.color.red))
                }
                enoughPercent.observe(viewLifecycleOwner) {
                    if (it) pbQuestion.progressTintList =
                        ColorStateList.valueOf(requireContext().getColor(R.color.green))
                    else pbQuestion.progressTintList =
                        ColorStateList.valueOf(requireContext().getColor(R.color.red))
                }
                gameResult.observe(viewLifecycleOwner) {
                    launchGameFinishedFragment(it)
                }
            }
        }
    }

    private fun setOnClickListeners() {
        for (optionIndex in 0 until tvOptionsList.size) tvOptionsList[optionIndex].setOnClickListener {
            setOnOptionClick(it)
        }
    }

    private fun setOnOptionClick(option: View) {
        viewModel.checkAnswer((option as TextView).text.toString().toInt())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.main_container, GameFinishedFragment.newInstance(gameResult)
            ).addToBackStack(null).commit()
    }


    @RequiresApi(33)
    private fun parseArguments() {
        level = requireArguments().getParcelable<Level>(KEY_LEVEL) as Level
    }

    companion object {
        const val NAME = "GameFragment"
        private const val KEY_LEVEL = "level"

        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }
    }
}