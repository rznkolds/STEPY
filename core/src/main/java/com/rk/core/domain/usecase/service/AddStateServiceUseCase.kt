package com.rk.core.domain.usecase.service

interface AddStateServiceUseCase {

    suspend operator fun invoke(state:Boolean)
}