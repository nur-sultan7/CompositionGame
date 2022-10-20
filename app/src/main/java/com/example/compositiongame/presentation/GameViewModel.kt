package com.example.compositiongame.presentation

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.compositiongame.R
import com.example.compositiongame.data.GameRepositoryImp
import com.example.compositiongame.domain.entiteis.GameResult
import com.example.compositiongame.domain.entiteis.GameSettings
import com.example.compositiongame.domain.entiteis.Level
import com.example.compositiongame.domain.entiteis.Question
import com.example.compositiongame.domain.usecases.GenerateQuestionUseCase
import com.example.compositiongame.domain.usecases.GetGameSettingsUseCase

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var gameSettings: GameSettings
    private lateinit var level: Level
    private val context = application
    private var countOfRightAnswers = 0
    private var countOfAnswers = 0
    private val repository = GameRepositoryImp
    private val generateQuestionUseCase = GenerateQuestionUseCase(repository)
    private val getGameSettings = GetGameSettingsUseCase(repository)
    private var timer: CountDownTimer? = null

    private var _timerTime = MutableLiveData<String>()
    val timerTime: LiveData<String>
        get() = _timerTime

    private var _question = MutableLiveData<Question>()
    val question: LiveData<Question>
        get() = _question

    private var _resultOfAnswers = MutableLiveData<String>()
    val resultOfAnswers: LiveData<String>
        get() = _resultOfAnswers

    private var _percentOfRightAnswers = MutableLiveData<Int>()
    val percentOfRightAnswers: LiveData<Int>
        get() = _percentOfRightAnswers

    private var _enoughRightAnswers = MutableLiveData<Boolean>()
    val enoughRightAnswers: LiveData<Boolean>
        get() = _enoughRightAnswers

    private var _enoughPercent = MutableLiveData<Boolean>()
    val enoughPercent: LiveData<Boolean>
        get() = _enoughPercent

    private var _minPercent = MutableLiveData<Int>()
    val minPercent: LiveData<Int>
        get() = _minPercent

    private var _gameResult = MutableLiveData<GameResult>()
    val gameResult: LiveData<GameResult>
        get() = _gameResult

    fun startGame(level: Level) {
        this.level = level
        getGameSettings()
        startTimer(gameSettings.gameTimeInSeconds)
        generateQuestion()
        updateResult()
    }


    fun checkAnswer(answer: Int) {
        if (answer == _question.value?.rightAnswer)
            countOfRightAnswers++
        countOfAnswers++
        updateResult()
        generateQuestion()
    }

    private fun updateResult() {
        _resultOfAnswers.value = context.getString(
            R.string.question_count_right_answers,
            countOfRightAnswers,
            gameSettings.minCountOfRightAnswers
        )
        _percentOfRightAnswers.value = calculatePercentOfRightAnswers()
        checkEnoughPercent()
        checkEnoughRightAnswers()
    }

    private fun getGameSettings() {
        gameSettings = getGameSettings.invoke(level)
        setMinPercentValue()
    }

    private fun setMinPercentValue() {
        _minPercent.value = gameSettings.minPercentOfRightAnswers
    }

    private fun checkEnoughRightAnswers() {
        _enoughRightAnswers.value = countOfRightAnswers >= gameSettings.minCountOfRightAnswers
    }

    private fun checkEnoughPercent() {
        _enoughPercent.value = percentOfRightAnswers.value?.let {
            it > gameSettings.minPercentOfRightAnswers
        } ?: false
    }

    private fun calculatePercentOfRightAnswers(): Int {
        return ((countOfRightAnswers / countOfAnswers.toDouble()) * 100).toInt()
    }

    private fun generateQuestion() {
        _question.value = generateQuestionUseCase.invoke(gameSettings.maxSumValue)
    }

    private fun startTimer(timeInSeconds: Int) {
        val timeInMillis = timeInSeconds * MILLIS_IN_SECOND
        timer = object : CountDownTimer(timeInMillis, MILLIS_IN_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                _timerTime.value = formattedTime(millisUntilFinished)
            }

            override fun onFinish() {
                finishGame()
            }

        }
        timer?.start()
    }

    private fun finishGame() {
        _gameResult.value = GameResult(
            enoughPercent.value == true && enoughRightAnswers.value == true,
            countOfRightAnswers,
            percentOfRightAnswers.value ?: 0,
            countOfAnswers,
            gameSettings
        )
    }

    private fun formattedTime(millis: Long): String {
        val seconds = millis / MILLIS_IN_SECOND
        val minutes = seconds / SECONDS_IN_MINUTE
        val secondLeft = seconds % SECONDS_IN_MINUTE
        return String.format("%02d:%02d", minutes, secondLeft)
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    companion object {
        const val MILLIS_IN_SECOND = 1000L
        const val SECONDS_IN_MINUTE = 60
    }
}