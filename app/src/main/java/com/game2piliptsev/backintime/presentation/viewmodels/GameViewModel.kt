package com.game2piliptsev.backintime.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.game2piliptsev.backintime.domain.entities.LevelEntity
import com.game2piliptsev.backintime.domain.usecases.GetCurrentScoreUseCase
import com.game2piliptsev.backintime.domain.usecases.GetLevelByNumberUseCase
import com.game2piliptsev.backintime.domain.usecases.SavePlayerAnswerUseCase
import com.game2piliptsev.backintime.domain.usecases.SubtractTwoPointsUseCase
import javax.inject.Inject

class GameViewModel @Inject constructor(
    private val getLevelByNumberUseCase: GetLevelByNumberUseCase,
    private val savePlayerAnswerUseCase: SavePlayerAnswerUseCase,
    private val getCurrentScoreUseCase: GetCurrentScoreUseCase,
    private val subtractTwoPointsUseCase: SubtractTwoPointsUseCase
) : ViewModel() {

    private val _getLevelLiveData = MutableLiveData<LevelEntity>()
    val getLevelLiveData: LiveData<LevelEntity>
        get() = _getLevelLiveData

    private val _getCurrentScoreLiveData = MutableLiveData<Int>()
    val getCurrentScoreLiveData: LiveData<Int>
        get() = _getCurrentScoreLiveData

    fun getLevelByNumber(levelNumber: Int) {
        _getLevelLiveData.value = getLevelByNumberUseCase.invoke(levelNumber)
    }

    fun savePlayerAnswer(number: Int, answer: Int) {
        savePlayerAnswerUseCase.invoke(number, answer)
        getCurrentScore()
    }

    fun subtractTwoPoints() {
        subtractTwoPointsUseCase.invoke()
        getCurrentScore()
    }

    private fun getCurrentScore() {
        _getCurrentScoreLiveData.value = getCurrentScoreUseCase.invoke()
    }
}