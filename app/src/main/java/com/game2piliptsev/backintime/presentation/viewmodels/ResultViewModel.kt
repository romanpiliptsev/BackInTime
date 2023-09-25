package com.game2piliptsev.backintime.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.game2piliptsev.backintime.domain.usecases.GetCountOfCorrectAnswersUseCase
import javax.inject.Inject

class ResultViewModel @Inject constructor(
    private val getCountOfCorrectAnswersUseCase: GetCountOfCorrectAnswersUseCase
) : ViewModel() {

    fun getCountOfCorrectAnswers(): Int = getCountOfCorrectAnswersUseCase.invoke()
}