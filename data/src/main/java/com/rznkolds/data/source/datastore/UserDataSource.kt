package com.rznkolds.data.source.datastore

import com.rznkolds.data.dto.Body
import com.rznkolds.data.dto.Temp
import kotlinx.coroutines.flow.Flow

interface UserDataSource {

    // Get and add onboarding complete method for datastore database

    suspend fun addOnboarding(complete: Boolean)

    fun getOnboarding(): Flow<Boolean?>

    // Get and add body data method for datastore database

    suspend fun addBodyData(weight: Int, height:Int)

    fun getBodyData(): Flow<Body>

    // Get and add target method for datastore database

    suspend fun addTarget(target: Int)

    fun getTarget(): Flow<Int?>

    // Get and add state service method for datastore database

    suspend fun addStateService(state: Boolean)

    fun getStateService(): Flow<Boolean?>

    // Get and add temp data methods for datastore database

    suspend fun addTempData(temp: Int, calorie:Double, kilometer:Double)

    fun getTempData(): Flow<Temp>

    // Get and add theme data methods for datastore database

    suspend fun addTheme(theme:Boolean)

    fun getTheme(): Flow<Boolean?>

    // Get and add language data methods for datastore database

    suspend fun addLanguage(language:String)

    fun getLanguage(): Flow<String?>

    // Get and add count ads methods for datastore database

    suspend fun addCountAd()

    fun getCountAd(): Flow<Int?>
}