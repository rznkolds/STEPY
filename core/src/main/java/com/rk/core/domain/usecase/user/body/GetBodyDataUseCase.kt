package com.rk.core.domain.usecase.user.body

import com.rk.core.data.dto.Body
import kotlinx.coroutines.flow.Flow

interface GetBodyDataUseCase {

    operator fun invoke(): Flow<Body>
}