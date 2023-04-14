package ru.sb066coder.composition.domain.usecases

import ru.sb066coder.composition.domain.entity.GameSettings
import ru.sb066coder.composition.domain.entity.Level
import ru.sb066coder.composition.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
    ) {

    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}