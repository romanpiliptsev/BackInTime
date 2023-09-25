package com.game2piliptsev.backintime.domain.usecases

import com.game2piliptsev.backintime.domain.repository.Repository
import javax.inject.Inject

class GetCurrentScoreUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke() = repository.getCurrentScore()
}