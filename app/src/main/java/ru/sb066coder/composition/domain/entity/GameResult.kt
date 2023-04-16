package ru.sb066coder.composition.domain.entity

import java.io.Serializable

data class GameResult(
    val winner: Boolean,
    val amountOfRightAnswers: Int,
    val amountOfQuestions: Int,
    val gameSettings: GameSettings
): Serializable