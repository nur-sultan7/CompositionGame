package com.example.compositiongame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.compositiongame.R
import com.example.compositiongame.databinding.FragmentGameFinishedBinding
import com.example.compositiongame.domain.entiteis.GameResult


class GameFinishedFragment() : Fragment() {

    private val args by navArgs<GameFinishedFragmentArgs>()
    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRetry.setOnClickListener {
            restartGame()
        }
//        requireActivity().onBackPressedDispatcher
//            .addCallback(
//                viewLifecycleOwner,
//                object : OnBackPressedCallback(true) {
//                    override fun handleOnBackPressed() {
//                        restartGame()
//                    }
//                }
//            )
        setGameResult()
    }

    private fun setGameResult() {
        binding.gameResult = args.gameResult
//        with(binding) {
//            imvEmojiResult.setImageResource(
//                if ( args.gameResult.winner)
//                    R.drawable.ic_smile
//                else
//                    R.drawable.ic_sad
//            )
//            tvRequiredRightAnswers.text = String.format(
//                getString(R.string.required_right_answers),
//                args.gameResult.gameSettings.minCountOfRightAnswers.toString()
//            )
//            tvCountOfRightAnswers.text = String.format(
//                getString(R.string.count_of_right_answers),
//                args.gameResult.countOfRightAnswers.toString()
//            )
//            tvRequiredPercentOfRightAnswers.text = String.format(
//                getString(R.string.required_right_answers_percent),
//                args.gameResult.gameSettings.minPercentOfRightAnswers.toString()
//            )
//            tvPercentOfRightAnswers.text = String.format(
//                getString(R.string.percent_of_right_answers),
//                args.gameResult.percentOfRightAnswers.toString()
//            )
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    @RequiresApi(33)
//    private fun parseArgs() {
//        gameResult = requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT) as GameResult
//    }

//    @RequiresApi(33)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        parseArgs()
//    }

    private fun restartGame() {
//        requireActivity().supportFragmentManager
//            .popBackStack(
//                GameFragment.NAME,
//                FragmentManager.POP_BACK_STACK_INCLUSIVE
//            )
        findNavController().popBackStack()
    }

//    companion object {
//        const val KEY_GAME_RESULT = "game result"
//        fun newInstance(gameResult: GameResult): GameFinishedFragment {
//            return GameFinishedFragment().apply {
//                arguments = Bundle().apply {
//                    putParcelable(KEY_GAME_RESULT, gameResult)
//                }
//            }
//        }
//    }
}