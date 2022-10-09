package com.example.compositiongame.domain.entiteis

import java.io.Serializable

data class GameSettings(
    val gameTimeInSeconds: Int,
    val maxSumValue: Int,
    val minCountOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int
):Serializable