package com.rznkolds.domain.usecase.steps.temp

import com.rznkolds.data.dto.Temp
import com.rznkolds.data.repository.user.UserRepository
import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.mapper.model.StepMapper
import com.rznkolds.domain.model.TempUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class GetTempDataUseCaseImpl @Inject constructor(
    private val repo: UserRepository,
): GetTempDataUseCase {

    override operator fun invoke(): Flow<TempUI> {

        return repo.getTempData().map {
            TempUI(
                step = it.step,
                calorie = it.calorie,
                kilometer = it.kilometer,
                bmi = it.bmi
            )
        }
    }
}