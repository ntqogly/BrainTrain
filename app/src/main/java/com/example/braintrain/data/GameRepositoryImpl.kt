package com.example.braintrain.data

import com.example.braintrain.domain.entity.GameSettings
import com.example.braintrain.domain.entity.Level
import com.example.braintrain.domain.entity.Question
import com.example.braintrain.domain.repository.GameRepository
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

object GameRepositoryImpl : GameRepository {

    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 2

    override fun generateQuestions(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val options = HashSet<Int>()

        val rightAnswers = sum - visibleNumber
        options.add(rightAnswers)
        val from = max(rightAnswers - countOfOptions, MIN_ANSWER_VALUE)
        val to = min(maxSumValue, rightAnswers + countOfOptions)
        while (options.size < countOfOptions) {
            options.add(Random.nextInt(from, to))
        }
        return Question(sum, visibleNumber, options.toList())
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when (level) {
            Level.TEST -> {
                GameSettings(10, 3, 50, 8)
            }

            Level.EASY -> {
                GameSettings(10, 10, 70, 60)
            }

            Level.NORMAL -> {
                GameSettings(20, 20, 80, 40)
            }

            Level.HARD -> {
                GameSettings(30, 30, 90, 40)
            }
        }
    }
}