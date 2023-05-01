package com.azlaan95.data.datasource.network.services

import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyExchangeRest {
    @GET("api/latest.json")
    suspend fun getExchangeRates(@Query("app_id") appId: String): Response<JsonObject>
}