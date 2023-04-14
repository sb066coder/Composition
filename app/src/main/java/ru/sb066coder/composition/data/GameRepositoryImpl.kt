package ru.sb066coder.composition.data

import ru.sb066coder.composition.domain.entity.GameSettings
import ru.sb066coder.composition.domain.entity.Level
import ru.sb066coder.composition.domain.entity.Question
import ru.sb066coder.composition.domain.repository.GameRepository
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

object GameRepositoryImpl : GameRepository {

    private const val MIN_SUM_VALUE = 2
    private const val MIN_VISIBLE_NUMBER_VALUE = 1


    override fun generateQuestion(maxSumValue: Int, amountOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_VISIBLE_NUMBER_VALUE, sum)
        val options = HashSet<Int>()
        val rightAnswer = sum - visibleNumber
        options.add(rightAnswer)
        val from = max(rightAnswer - amountOfOptions, MIN_VISIBLE_NUMBER_VALUE)
        val to = min(maxSumValue, rightAnswer + amountOfOptions)
        while (options.size < amountOfOptions) {
            options.add(Random.nextInt(from, to))
        }
        return Question(sum, visibleNumber, options.toList())
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when (level) {
            Level.TEST -> GameSettings(
                10,
                3,
                50,
                8
            )
            Level.EASY -> GameSettings(
                10,
                10,
                70,
                60
            )
            Level.NORMAL -> GameSettings(
                20,
                20,
                80,
                40
            )
            Level.HARD -> GameSettings(
                30,
                30,
                90,
                40
            )
        }
    }
}