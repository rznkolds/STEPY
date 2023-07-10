package com.rk.core.domain.repository

import com.rk.core.data.dto.Body
import com.rk.core.data.dto.Temp
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    // Get and add onboarding methods for repository

    suspend fun addOnboarding(complete: Boolean)

    fun getOnboarding(): Flow<Boolean?>

    // Get and add body data method for repository

    suspend fun addBodyData(weight: Int, height:Int)

    fun getBodyData():Flow<Body>

    // Get and add target methods for repository

    suspend fun addTarget(target: Int)

    fun getTarget(): Flow<Int?>

    // Get and add temp data methods for repository

    suspend fun addTempData(temp: Int, calorie:Double, kilometer:Double)

    fun getTempData(): Flow<Temp>

    // Get and add state service method for repository

    suspend fun addStateService(state: Boolean)

    fun getStateService(): Flow<Boolean?>

    // Get and add theme data methods for repository

    suspend fun addTheme(theme: Boolean)

    fun getTheme(): Flow<Boolean?>

    // Get and add language data methods for repository

    suspend fun addLanguage(language: String)

    fun getLanguage(): Flow<String?>

    // Get and add count ads methods for repository

    suspend fun addCountAd()

    fun getCountAd(): Flow<Int?>
}