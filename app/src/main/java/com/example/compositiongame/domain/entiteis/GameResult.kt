package com.example.compositiongame.domain.entiteis

import java.io.Serializable

data class GameResult(
    val winner: Boolean,
    val countOfRightAnswers: Int,
    val percentOfRightAnswers: Int,
    val countOfQuestion: Int,
    val gameSettings: GameSettings
) : Serializable