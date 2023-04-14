package ru.sb066coder.composition.domain.usecases

import ru.sb066coder.composition.domain.entity.Question
import ru.sb066coder.composition.domain.repository.GameRepository

class GenerateQuestionUseCase(
    private val repository: GameRepository
) {
    operator fun invoke(maxSumValue: Int): Question {
        return repository.generateQuestion(maxSumValue, AMOUNT_OF_OPTIONS)
    }

    private companion object {
        private const val AMOUNT_OF_OPTIONS = 6
    }
}