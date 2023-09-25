package com.game2piliptsev.backintime.domain.repository

import com.game2piliptsev.backintime.domain.entities.LevelEntity

interface Repository {

    fun savePlayerAnswer(number: Int, answer: Int)
    fun getLevelByNumber(number: Int): LevelEntity
    fun getCountOfCorrectAnswers(): Int
    fun getCurrentScore(): Int
    fun subtractTwoPoints()
}