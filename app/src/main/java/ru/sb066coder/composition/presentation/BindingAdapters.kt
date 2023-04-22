package ru.sb066coder.composition.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
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

@BindingAdapter("setIntToText")
fun bindIntToString(textView: TextView, number: Int) {
    textView.text = number.toString()
}

@BindingAdapter("setOptionalTextColor")
fun setColorByState(textView: TextView, goodState: Boolean) {
    textView.setTextColor(getColorByState(textView.context, goodState)) // ContextCompat.getColor(requireContext(), colorResId)
}

@BindingAdapter("setOptionalColor")
fun setProgressBarColorByState(progressBar: ProgressBar, goodState: Boolean) {
    val color = getColorByState(progressBar.context, goodState)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

private fun getColorByState(context: Context, goodState: Boolean): Int {
    val colorResId = if (goodState) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}


interface OnOptionClickListener {
    fun onOptionClick(option: Int)
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener: OnOptionClickListener) {
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}