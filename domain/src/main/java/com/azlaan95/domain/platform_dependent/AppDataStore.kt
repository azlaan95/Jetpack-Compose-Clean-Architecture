package com.azlaan95.domain.platform_dependent

interface AppDataStore {
    fun <T> getString(key: String): T
    suspend fun saveString(value: String, key: String)
}