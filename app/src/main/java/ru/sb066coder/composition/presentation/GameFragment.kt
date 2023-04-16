package ru.sb066coder.composition.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.sb066coder.composition.R
import ru.sb066coder.composition.databinding.FragmentGameBinding
import ru.sb066coder.composition.domain.entity.GameResult
import ru.sb066coder.composition.domain.entity.GameSettings
import ru.sb066coder.composition.domain.entity.Level

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    private lateinit var level: Level

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

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
        launchGameFinishedFragment(
            GameResult(
                true,
                20,
                30,
                GameSettings(45, 11, 45, 2)
            ))
    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        binding.tvOption1.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(
                    R.id.main_container, GameFinishedFragment.newInstance(gameResult)
                )
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs() {
        level = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getSerializable(KEY_LEVEL, Level::class.java)!!
        } else {
            requireArguments().getSerializable(KEY_LEVEL) as Level
        }
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