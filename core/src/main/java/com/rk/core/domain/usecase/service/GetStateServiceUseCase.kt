package com.rk.core.domain.usecase.service

import kotlinx.coroutines.flow.Flow

interface GetStateServiceUseCase {

    operator fun invoke(): Flow<Boolean?>
}