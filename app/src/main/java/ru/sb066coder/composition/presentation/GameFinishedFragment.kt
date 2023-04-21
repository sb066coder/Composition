package ru.sb066coder.composition.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.sb066coder.composition.R
import ru.sb066coder.composition.databinding.FragmentGameFinishedBinding
import ru.sb066coder.composition.domain.entity.GameResult

class GameFinishedFragment : Fragment() {

    private lateinit var gameResult: GameResult

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        bindViews()
    }

    private fun bindViews() {
        val context = requireContext()
        with(binding) {
            ivEmojiResult.setImageResource(getSmileResId())
            tvRequiredAnswers.text = String.format(
                context.getString(R.string.required_answers_amount),
                gameResult.gameSettings.minAmountOfRightAnswers.toString()
            )
            tvScoreAnswers.text = String.format(
                context.getString(R.string.score_answers),
                gameResult.amountOfRightAnswers.toString()
            )
            tvRequiredPercentage.text = String.format(
                context.getString(R.string.required_answers_percent),
                gameResult.gameSettings.minPercentOfRightAnswers.toString()
            )
            tvScorePercentage.text = String.format(
                context.getString(R.string.score_percent), getScorePercentage().toString()
            )
        }
    }

    private fun setupListeners() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                retryGame()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        binding.btnRetry.setOnClickListener {
            retryGame()
        }
    }

    private fun getSmileResId(): Int {
        return if (gameResult.winner) {
            R.drawable.ic_win_smile
        } else {
            R.drawable.ic_lose_smile
        }
    }
    private fun getScorePercentage(): Int {
        return if (gameResult.amountOfQuestions == 0) {
            0
        } else {
            (gameResult.amountOfRightAnswers / gameResult.amountOfQuestions.toDouble() * 100).toInt()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @Suppress("DEPRECATION")
    private fun parseArgs() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable(KEY_GAME_RESULT, GameResult::class.java)?.let {
                gameResult = it
            }
        } else {
            requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)!!.let {
                gameResult = it
            }
        }
    }

    private fun retryGame() {
        requireActivity().supportFragmentManager.popBackStack(
            GameFragment.NAME, FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    companion object {

        private const val KEY_GAME_RESULT = "game_result"

        fun newInstance(gameResult: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, gameResult)
                }
            }
        }
    }
}