package com.rznkolds.domain.usecase.user.body

import com.rznkolds.domain.model.BodyUI
import kotlinx.coroutines.flow.Flow

interface GetBodyDataUseCase {

    operator fun invoke(): Flow<BodyUI>
}