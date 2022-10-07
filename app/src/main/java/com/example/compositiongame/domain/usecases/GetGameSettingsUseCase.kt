package com.example.compositiongame.domain.usecases

import com.example.compositiongame.domain.entiteis.GameSettings
import com.example.compositiongame.domain.entiteis.Level
import com.example.compositiongame.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {
    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSetting(level)
    }
}