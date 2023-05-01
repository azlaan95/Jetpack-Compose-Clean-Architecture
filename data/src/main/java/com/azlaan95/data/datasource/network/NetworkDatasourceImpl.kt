package com.azlaan95.data.datasource.network

import com.azlaan95.data.datasource.network.services.CurrencyExchangeRest
import com.google.gson.GsonBuilder
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class NetworkDatasourceImpl : NetworkDatasource {

    private val okHttpClient by lazy { OkHttpClient() }

    private val client by lazy {
        okHttpClient.newBuilder()
            .connectTimeout(16, TimeUnit.SECONDS)
            .writeTimeout(16, TimeUnit.SECONDS)
            .readTimeout(16, TimeUnit.SECONDS)
            .dispatcher(Dispatcher()).build()
    }

    private fun getRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client)
            .build()
    }

    override fun getCurrencyRests(baseUrl: String): CurrencyExchangeRest {
        return getRetrofit(baseUrl).create(CurrencyExchangeRest::class.java)
    }
}
