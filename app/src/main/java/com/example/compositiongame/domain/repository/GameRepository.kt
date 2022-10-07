package com.example.compositiongame.domain.repository

import com.example.compositiongame.domain.entiteis.GameSettings
import com.example.compositiongame.domain.entiteis.Level
import com.example.compositiongame.domain.entiteis.Question

interface GameRepository {
    fun generateQuestion(
        maxSum: Int,
        optionsCount: Int
    ): Question

    fun getGameSetting(level: Level): GameSettings
}