package com.example.compositiongame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.compositiongame.R
import com.example.compositiongame.databinding.FragmentChooseLevelBinding
import com.example.compositiongame.domain.entiteis.Level


class ChooseLevelFragment : Fragment() {
    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding is null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.levelTest.setOnClickListener {
            launchGameFragment(Level.TEST)
        }
        binding.levelEasy.setOnClickListener {
            launchGameFragment(Level.EASY)
        }
        binding.levelNormal.setOnClickListener {
            launchGameFragment(Level.NORMAL)
        }
        binding.levelHard.setOnClickListener {
            launchGameFragment(Level.HARD)
        }
    }

    private fun launchGameFragment(level: Level) {
//        requireActivity().supportFragmentManager.beginTransaction()
//            .replace(R.id.main_container, GameFragment.newInstance(level))
//            .addToBackStack(GameFragment.NAME)
//            .commit()

//        val args = Bundle().apply {
//            putParcelable(GameFragment.KEY_LEVEL, level)
//        }
//        findNavController().navigate(R.id.action_chooseLevelFragment_to_gameFragment, args)
        findNavController().navigate(
            ChooseLevelFragmentDirections.actionChooseLevelFragmentToGameFragment(
                level
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val NAME = "ChooseLevelFragment"
        fun newInstance(): ChooseLevelFragment {
            return ChooseLevelFragment()
        }
    }
}