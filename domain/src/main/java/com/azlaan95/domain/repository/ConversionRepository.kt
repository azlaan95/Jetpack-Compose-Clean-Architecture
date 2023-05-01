package com.azlaan95.domain.repository

import com.azlaan95.domain.base.BaseResponse
import com.azlaan95.domain.entities.exchange_rates.ExchangeRatesResponse

interface ConversionRepository {
    suspend fun getExchangeRates(): BaseResponse<ExchangeRatesResponse>
}