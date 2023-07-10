package com.rk.core.domain.usecase.user.body

import com.rk.core.data.dto.Body
import com.rk.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBodyDataUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : GetBodyDataUseCase {

    override operator fun invoke(): Flow<Body> = repo.getBodyData()
}