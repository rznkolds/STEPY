package com.rznkolds.data.repository.user

import com.rznkolds.data.dto.Body
import com.rznkolds.data.dto.Temp
import com.rznkolds.data.source.datastore.UserDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor (
    private val source: UserDataSource
) : UserRepository {

    // Get and add onboarding methods for repository

    override suspend fun addOnboarding(complete: Boolean) = source.addOnboarding(complete)

    override fun getOnboarding(): Flow<Boolean?> = source.getOnboarding()

    // Get and add body data method for repository

    override suspend fun addBodyData(weight: Int, height: Int) = source.addBodyData(weight, height)

    override fun getBodyData() : Flow<Body> = source.getBodyData()

    // Get and add target methods for repository

    override suspend fun addTarget(target: Int) = source.addTarget(target)

    override fun getTarget(): Flow<Int?> = source.getTarget()

    // Get and add temp step data methods for repository

    override suspend fun addTempData(temp: Int, calorie:Double, kilometer:Double) = source.addTempData(temp, calorie, kilometer)

    override fun getTempData(): Flow<Temp> = source.getTempData()

    // Get and add state service method for repository

    override suspend fun addStateService(state: Boolean) = source.addStateService(state)

    override fun getStateService(): Flow<Boolean?> = source.getStateService()

    // Get and add theme data methods for repository

    override suspend fun addTheme(theme: Boolean) = source.addTheme(theme)

    override fun getTheme(): Flow<Boolean?> = source.getTheme()

    // Get and add language data methods for repository

    override suspend fun addLanguage(language: String) = source.addLanguage(language)

    override fun getLanguage(): Flow<String?> = source.getLanguage()

    // Get and add count ads methods for repository

    override suspend fun addCountAd() = source.addCountAd()

    override fun getCountAd(): Flow<Int?> = source.getCountAd()
}