package com.rk.core.data.source.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.rk.core.common.Constants.ADS_KEY
import com.rk.core.common.Constants.BMI_KEY
import com.rk.core.common.Constants.HEIGHT_KEY
import com.rk.core.common.Constants.LANGUAGE_KEY
import com.rk.core.common.Constants.ONBOARDING_KEY
import com.rk.core.common.Constants.SERVICE_KEY
import com.rk.core.common.Constants.TARGET_KEY
import com.rk.core.common.Constants.TEMP_CALORIE_KEY
import com.rk.core.common.Constants.TEMP_METER_KEY
import com.rk.core.common.Constants.TEMP_STEP_KEY
import com.rk.core.common.Constants.THEME_KEY
import com.rk.core.common.Constants.WEIGHT_KEY
import com.rk.core.data.dto.Body
import com.rk.core.data.dto.Temp
import com.rk.core.domain.source.local.UserDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.intellij.lang.annotations.Language
import javax.inject.Inject

private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "DATASTORE_SOURCE")

class UserDataSourceImpl @Inject constructor(
    private val context: Context
): UserDataSource {

    // Get and add onboarding complete method for datastore database

    override suspend fun addOnboarding(complete: Boolean) {

        context.datastore.edit {

            it[ONBOARDING_KEY] = complete
        }
    }

    override fun getOnboarding(): Flow<Boolean?> = context.datastore.data.map {

        it[ONBOARDING_KEY]
    }

    // Get and add body data method for datastore database

    override suspend fun addBodyData(weight: Int, height:Int) {

        context.datastore.edit {

            it[BMI_KEY] = (weight / (height / 100.0 * height / 100.0)).toInt()
            it[WEIGHT_KEY] = weight
            it[HEIGHT_KEY] = height
        }
    }

    override fun getBodyData(): Flow<Body> = context.datastore.data.map {

        Body(
            it[WEIGHT_KEY],
            it[HEIGHT_KEY]
        )
    }

    // Get and add target method for datastore database

    override suspend fun addTarget(target: Int) {

        context.datastore.edit {

            it[TARGET_KEY] = target
        }
    }

    override fun getTarget(): Flow<Int?> = context.datastore.data.map {

        it[TARGET_KEY]
    }

    // Get and add state service method for datastore database

    override suspend fun addStateService(state: Boolean) {

        context.datastore.edit {

            it[SERVICE_KEY] = state
        }
    }

    override fun getStateService(): Flow<Boolean?> = context.datastore.data.map {

        it[SERVICE_KEY]
    }

    // Get and add temp data methods for datastore database

    override suspend fun addTempData(temp: Int, calorie:Double, kilometer:Double) {

        context.datastore.edit {

            it[TEMP_STEP_KEY] = temp
            it[TEMP_CALORIE_KEY] = calorie
            it[TEMP_METER_KEY] = kilometer
        }
    }

    override fun getTempData(): Flow<Temp> = context.datastore.data.map {

        Temp(
            it[TEMP_STEP_KEY],
            it[TEMP_CALORIE_KEY],
            it[TEMP_METER_KEY],
            it[BMI_KEY]
        )
    }

    // Get and add theme data methods for datastore database

    override suspend fun addTheme(theme: Boolean) {

        context.datastore.edit {

            it[THEME_KEY] = theme
        }
    }

    override fun getTheme(): Flow<Boolean?> = context.datastore.data.map {

        it[THEME_KEY]
    }

    // Get and add language data methods for datastore database

    override suspend fun addLanguage(language: String) {

        context.datastore.edit {

            it[LANGUAGE_KEY] = language
        }
    }

    override fun getLanguage(): Flow<String?> = context.datastore.data.map {

        it[LANGUAGE_KEY]
    }

    override suspend fun addCountAd() {

        context.datastore.edit {

            it[ADS_KEY] = (it[ADS_KEY] ?: 0) + 1
        }
    }

    override fun getCountAd(): Flow<Int?> = context.datastore.data.map {

        it[ADS_KEY]
    }
}