package com.azlaan95.domain.usecase

import com.azlaan95.domain.base.BaseResponse
import com.azlaan95.domain.entities.exchange_rates.ExchangeRatesResponse

interface ConversionUseCase {
    suspend fun fetchExchangeRates(): BaseResponse<ExchangeRatesResponse>
}