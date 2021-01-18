package com.alvin.hiltsample.local.dataStore

import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.emptyPreferences
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DataStore @Inject constructor(private val dataStore: DataStore<Preferences>) {

    companion object {

        const val INT = 0
        const val FLOAT = 1
        const val DOUBLE = 2
        const val LONG = 3
        const val STRING = 4
        const val BOOLEAN = 5

        const val AUTH_TOKEN = "AUTH_TOKEN"

        const val SAVE_INT = "IS_DARK_MODE_INT"
        const val SAVE_STRING = "IS_DARK_MODE_String"
        const val SAVE_BOOL = "IS_DARK_MODE_Bool"

    }

    suspend fun setValue(mode: Int, keyName: String, value: Any) {
        dataStore.edit { preferences ->
            when (mode) {
                INT -> {
                    preferences[preferencesKey<Int>(keyName)] = value as Int
                }
                FLOAT -> {
                    preferences[preferencesKey<Float>(keyName)] = value as Float
                }
                DOUBLE -> {
                    preferences[preferencesKey<Double>(keyName)] = value as Double
                }
                LONG -> {
                    preferences[preferencesKey<Long>(keyName)] = value as Long
                }
                STRING -> {
                    preferences[preferencesKey<String>(keyName)] = value as String
                }
                BOOLEAN -> {
                    preferences[preferencesKey<Boolean>(keyName)] = value as Boolean
                }
            }

        }
    }


    fun getValue(mode: Int, keyName: String): Flow<Any?> {
        return dataStore.data
            .catch {
                if (it is IOException) {
                    it.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }
            .map { preference ->
                when (mode) {
                    INT -> {
                        preference[preferencesKey<Int>(keyName)]
                    }
                    FLOAT -> {
                        preference[preferencesKey<Float>(keyName)]
                    }
                    DOUBLE -> {
                        preference[preferencesKey<Int>(keyName)]
                    }
                    LONG -> {
                        preference[preferencesKey<Int>(keyName)]
                    }
                    STRING -> {
                        preference[preferencesKey<String>(keyName)]
                    }
                    BOOLEAN -> {
                        preference[preferencesKey<Boolean>(keyName)]
                    }
                    else -> preference[preferencesKey<String>(keyName)]
                }


            }

    }


}