package com.rznkolds.domain.usecase.service

import kotlinx.coroutines.flow.Flow

interface GetStateServiceUseCase {

    operator fun invoke(): Flow<Boolean?>
}