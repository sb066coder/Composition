package ru.sb066coder.composition.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResult(
    val winner: Boolean,
    val amountOfRightAnswers: Int,
    val amountOfQuestions: Int,
    val gameSettings: GameSettings
): Parcelable