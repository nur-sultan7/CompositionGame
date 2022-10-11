package com.example.compositiongame.domain.entiteis

data class Question(
    val sum: Int,
    val visibleNumber: Int,
    val options: List<Int>
) {
    val rightAnswer = sum - visibleNumber
}