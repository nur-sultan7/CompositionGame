package com.example.compositiongame.domain.entiteis

data class GameSettings(
    val gameTimeInSeconds: Int,
    val maxSumValue: Int,
    val minCountOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int
)