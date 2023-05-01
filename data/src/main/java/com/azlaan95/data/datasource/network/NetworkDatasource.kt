package com.azlaan95.data.datasource.network

import com.azlaan95.data.datasource.network.services.CurrencyExchangeRest

interface NetworkDatasource {
    fun getCurrencyRests(baseUrl: String): CurrencyExchangeRest
}