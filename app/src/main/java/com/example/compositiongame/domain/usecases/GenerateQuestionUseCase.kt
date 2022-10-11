package com.example.compositiongame.domain.usecases

import com.example.compositiongame.domain.entiteis.Question
import com.example.compositiongame.domain.repository.GameRepository

class GenerateQuestionUseCase(
    private val repository: GameRepository
) {
    operator fun invoke(maxSum: Int): Question {
       return repository.generateQuestion(maxSum, COUNT_OF_OPTIONS)
    }

    companion object {
        private const val COUNT_OF_OPTIONS = 6
    }
}