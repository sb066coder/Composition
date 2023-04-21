package ru.sb066coder.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.sb066coder.composition.R
import ru.sb066coder.composition.databinding.FragmentGameFinishedBinding

class GameFinishedFragment : Fragment() {


    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    private val args by navArgs<GameFinishedFragmentArgs>()

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
                args.gameResult.gameSettings.minAmountOfRightAnswers.toString()
            )
            tvScoreAnswers.text = String.format(
                context.getString(R.string.score_answers),
                args.gameResult.amountOfRightAnswers.toString()
            )
            tvRequiredPercentage.text = String.format(
                context.getString(R.string.required_answers_percent),
                args.gameResult.gameSettings.minPercentOfRightAnswers.toString()
            )
            tvScorePercentage.text = String.format(
                context.getString(R.string.score_percent), getScorePercentage().toString()
            )
        }
    }

    private fun setupListeners() {
        binding.btnRetry.setOnClickListener {
            retryGame()
        }
    }

    private fun getSmileResId(): Int {
        return if (args.gameResult.winner) {
            R.drawable.ic_win_smile
        } else {
            R.drawable.ic_lose_smile
        }
    }
    private fun getScorePercentage(): Int {
        return if (args.gameResult.amountOfQuestions == 0) {
            0
        } else {
            (args.gameResult.amountOfRightAnswers / args.gameResult.amountOfQuestions.toDouble() * 100).toInt()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun retryGame() {
        findNavController().popBackStack()
    }

}