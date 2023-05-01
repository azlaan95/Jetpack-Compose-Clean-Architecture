package com.azlaan95.domain.usecase

import com.azlaan95.domain.base.BaseResponse
import com.azlaan95.domain.di.DomainModule
import com.azlaan95.domain.entities.exchange_rates.ExchangeRatesResponse
import com.azlaan95.domain.repository.ConversionRepository

class ConversionUseCaseImpl(var repository: ConversionRepository = DomainModule.getConversionRepository()) :
    ConversionUseCase {
    override suspend fun fetchExchangeRates(): BaseResponse<ExchangeRatesResponse> {
        return try {
            val baseResponse: BaseResponse<ExchangeRatesResponse> =
                repository.getExchangeRates()
            baseResponse
        } catch (ex: Exception) {
            BaseResponse(
                null, code = 501, message = ex.message ?: ex.cause?.message ?: ex.localizedMessage
                ?: "Unable To Get Exception"
            )
        }
    }
}