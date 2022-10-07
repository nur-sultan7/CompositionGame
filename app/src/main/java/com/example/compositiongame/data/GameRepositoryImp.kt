package com.example.compositiongame.data

import com.example.compositiongame.domain.entiteis.GameSettings
import com.example.compositiongame.domain.entiteis.Level
import com.example.compositiongame.domain.entiteis.Question
import com.example.compositiongame.domain.repository.GameRepository
import java.lang.Integer.max
import java.lang.Integer.min
import kotlin.random.Random

object GameRepositoryImp : GameRepository {
    private const val MIN_SUM_VALUE = 5
    private const val MIN_VISIBLE_NUMBER_VALUE = 1
    override fun generateQuestion(maxSum: Int, optionsCount: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSum + 1)
        val visibleNumber = Random.nextInt(MIN_VISIBLE_NUMBER_VALUE, sum)
        val rightAnswer = sum - visibleNumber
        val from = max(rightAnswer - optionsCount, MIN_VISIBLE_NUMBER_VALUE)
        val to = min(maxSum, rightAnswer + optionsCount)
        val options = HashSet<Int>()
        options.add(rightAnswer)
        while (options.size < optionsCount) {
            options.add(Random.nextInt(from, to))
        }
        return Question(sum, visibleNumber, options.toList())
    }

    override fun getGameSetting(level: Level): GameSettings {
        return when (level) {
            Level.TEST -> GameSettings(
                5,
                10,
                3,
                30
            )
            Level.EASY -> GameSettings(
                50,
                30,
                6,
                50
            )
            Level.NORMAL -> GameSettings(
                40,
                60,
                10,
                70
            )
            Level.HARD -> GameSettings(
                20,
                100,
                10,
                80
            )
        }
    }
}