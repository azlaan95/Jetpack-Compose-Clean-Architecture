package com.azl.currencyconvertorapp.platform_dependents

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.azlaan95.domain.platform_dependent.AppDataStore
import kotlinx.coroutines.flow.map


class AppDataStoreImpl(private val context: Context) : AppDataStore {

    companion object {
        private val Context.dataStoree: DataStore<Preferences> by preferencesDataStore("AppStorage")
        const val EXCHANGE_RESPONSE = "EXCHANGE_RESPONSE"
    }

    /**
     * Call it like below:
     * { appDataStore.getString<Flow<String?>> }
     * To make it Decouple i have made it Generic
     */
    override fun <T> getString(key: String): T {
        return context.dataStoree.data
            .map { preferences ->
                preferences[stringPreferencesKey(key)] ?: ""
            } as T
    }

    override suspend fun saveString(value: String, key: String) {
        context.dataStoree.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }


}