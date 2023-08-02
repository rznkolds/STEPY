package com.rznkolds.data.common

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {

    // STANDARD DATA KEYS
    val ONBOARDING_KEY = booleanPreferencesKey("onboarding_data_key")
    val LANGUAGE_KEY = stringPreferencesKey("language_data_key")
    val ADS_KEY = intPreferencesKey("ads_data_key")
    val SERVICE_KEY = booleanPreferencesKey("state_data_key")
    val THEME_KEY = booleanPreferencesKey("theme_data_key")
    val TARGET_KEY = intPreferencesKey("target_data_key")
    val WEIGHT_KEY = intPreferencesKey("weight_data_key")
    val HEIGHT_KEY = intPreferencesKey("height_data_key")
    val BMI_KEY = intPreferencesKey("bmi_data_key")

    // TEMP DATA KEYS
    val TEMP_CALORIE_KEY = doublePreferencesKey("temp_calorie_data_key")
    val TEMP_METER_KEY = doublePreferencesKey("temp_kilometer_data_key")
    val TEMP_STEP_KEY = intPreferencesKey("temp_step_data_key")
}