package com.rznkolds.domain.usecase.user.body

import com.rznkolds.data.repository.user.UserRepository
import com.rznkolds.domain.model.BodyUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetBodyDataUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : GetBodyDataUseCase {

    override operator fun invoke(): Flow<BodyUI> {

        return repo.getBodyData().map {
            BodyUI(
                it.weight,
                it.height
            )
        }
    }
}