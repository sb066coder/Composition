package ru.sb066coder.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.sb066coder.composition.R
import ru.sb066coder.composition.databinding.FragmentChooseLevelBinding
import ru.sb066coder.composition.domain.entity.Level


class ChooseLevelFragment: Fragment() {

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnLevelTest.setOnClickListener {
            launchGameFragment(Level.TEST)
        }
        binding.btnLevelEasy.setOnClickListener {
            launchGameFragment(Level.EASY)
        }
        binding.btnLevelNormal.setOnClickListener {
            launchGameFragment(Level.NORMAL)
        }
        binding.btnLevelHard.setOnClickListener {
            launchGameFragment(Level.HARD)
        }
    }

    private fun launchGameFragment(level: Level) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFragment.newInstance(level))
            .addToBackStack(GameFragment.NAME)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance(): ChooseLevelFragment {
            return ChooseLevelFragment()
        }
    }
}