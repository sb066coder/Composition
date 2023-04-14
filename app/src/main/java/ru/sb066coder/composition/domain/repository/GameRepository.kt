package ru.sb066coder.composition.domain.repository

import ru.sb066coder.composition.domain.entity.GameSettings
import ru.sb066coder.composition.domain.entity.Level
import ru.sb066coder.composition.domain.entity.Question

interface GameRepository {
    fun generateQuestion(
        maxSumValue: Int,
        amountOfOptions: Int
    ): Question
    fun getGameSettings(level: Level): GameSettings
}