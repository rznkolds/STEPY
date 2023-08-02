package com.rznkolds.data.source.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.rznkolds.data.common.Constants
import com.rznkolds.data.dto.Body
import com.rznkolds.data.dto.Temp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "DATASTORE_SOURCE")
class UserDataSourceImpl @Inject constructor(
    private val context: Context
): UserDataSource {

    // Get and add onboarding complete method for datastore database

    override suspend fun addOnboarding(complete: Boolean) {

        context.datastore.edit {

            it[Constants.ONBOARDING_KEY] = complete
        }
    }

    override fun getOnboarding(): Flow<Boolean?> = context.datastore.data.map {

        it[Constants.ONBOARDING_KEY]
    }

    // Get and add body data method for datastore database

    override suspend fun addBodyData(weight: Int, height:Int) {

        context.datastore.edit {

            it[Constants.BMI_KEY] = (weight / (height / 100.0 * height / 100.0)).toInt()
            it[Constants.WEIGHT_KEY] = weight
            it[Constants.HEIGHT_KEY] = height
        }
    }

    override fun getBodyData(): Flow<Body> = context.datastore.data.map {

        Body(
            it[Constants.WEIGHT_KEY],
            it[Constants.HEIGHT_KEY]
        )
    }

    // Get and add target method for datastore database

    override suspend fun addTarget(target: Int) {

        context.datastore.edit {

            it[Constants.TARGET_KEY] = target
        }
    }

    override fun getTarget(): Flow<Int?> = context.datastore.data.map {

        it[Constants.TARGET_KEY]
    }

    // Get and add state service method for datastore database

    override suspend fun addStateService(state: Boolean) {

        context.datastore.edit {

            it[Constants.SERVICE_KEY] = state
        }
    }

    override fun getStateService(): Flow<Boolean?> = context.datastore.data.map {

        it[Constants.SERVICE_KEY]
    }

    // Get and add temp data methods for datastore database

    override suspend fun addTempData(temp: Int, calorie:Double, kilometer:Double) {

        context.datastore.edit {

            it[Constants.TEMP_STEP_KEY] = temp
            it[Constants.TEMP_CALORIE_KEY] = calorie
            it[Constants.TEMP_METER_KEY] = kilometer
        }
    }

    override fun getTempData(): Flow<Temp> = context.datastore.data.map {

        Temp(
            it[Constants.TEMP_STEP_KEY],
            it[Constants.TEMP_CALORIE_KEY],
            it[Constants.TEMP_METER_KEY],
            it[Constants.BMI_KEY]
        )
    }

    // Get and add theme data methods for datastore database

    override suspend fun addTheme(theme: Boolean) {

        context.datastore.edit {

            it[Constants.THEME_KEY] = theme
        }
    }

    override fun getTheme(): Flow<Boolean?> = context.datastore.data.map {

        it[Constants.THEME_KEY]
    }

    // Get and add language data methods for datastore database

    override suspend fun addLanguage(language: String) {

        context.datastore.edit {

            it[Constants.LANGUAGE_KEY] = language
        }
    }

    override fun getLanguage(): Flow<String?> = context.datastore.data.map {

        it[Constants.LANGUAGE_KEY]
    }

    override suspend fun addCountAd() {

        context.datastore.edit {

            it[Constants.ADS_KEY] = (it[Constants.ADS_KEY] ?: 0) + 1
        }
    }

    override fun getCountAd(): Flow<Int?> = context.datastore.data.map {

        it[Constants.ADS_KEY]
    }
}