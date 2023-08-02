package com.rznkolds.domain.usecase.service

interface AddStateServiceUseCase {

    suspend operator fun invoke(state:Boolean)
}