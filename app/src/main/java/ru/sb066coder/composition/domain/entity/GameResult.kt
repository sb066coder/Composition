package ru.sb066coder.composition.domain.entity

data class GameResult(
    val winner: Boolean,
    val amountOfRightAnswers: Int,
    val amountOfQuestions: Int,
    val gameSettings: GameSettings
)