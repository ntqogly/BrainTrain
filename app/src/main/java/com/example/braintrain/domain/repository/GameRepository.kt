package com.example.braintrain.domain.repository

import com.example.braintrain.domain.entity.GameSettings
import com.example.braintrain.domain.entity.Level
import com.example.braintrain.domain.entity.Question

interface GameRepository {

    fun generateQuestions(maxSumValue: Int, countOfOptions: Int): Question

    fun getGameSettings(level: Level): GameSettings
}
