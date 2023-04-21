package ru.sb066coder.composition.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import ru.sb066coder.composition.R
import ru.sb066coder.composition.domain.entity.GameResult

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_answers_amount),
        count
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_answers_percent),
        count
    )
}
@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percent), getScorePercentage(gameResult).toString()
    )
}

fun getScorePercentage(gameResult: GameResult): Int {
    return if (gameResult.amountOfQuestions == 0) {
        0
    } else {
        (gameResult.amountOfRightAnswers / gameResult.amountOfQuestions.toDouble() * 100).toInt()
    }
}

@BindingAdapter("emojiResult")
fun bindEmojiResult(imageView: ImageView, win: Boolean) {
    imageView.setImageResource(
        if (win) {
            R.drawable.ic_win_smile
        } else {
            R.drawable.ic_lose_smile
        }
    )
}