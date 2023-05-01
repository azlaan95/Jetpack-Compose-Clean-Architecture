package com.azlaan95.domain.repository

import com.azlaan95.data.datasource.network.services.CurrencyExchangeRest
import com.azlaan95.domain.BuildConfig
import com.azlaan95.domain.base.BaseRepository
import com.azlaan95.domain.base.BaseResponse
import com.azlaan95.domain.base.GsonConvertorHelper
import com.azlaan95.domain.entities.exchange_rates.ExchangeRatesResponse
import com.google.gson.JsonObject
import retrofit2.Response


class ConversionRepositoryImpl : BaseRepository(), ConversionRepository {
    override suspend fun getExchangeRates(): BaseResponse<ExchangeRatesResponse> {
        var currencyRest: CurrencyExchangeRest =
            getNetworkSource().getCurrencyRests(BuildConfig.BASE_URL)
        var response: Response<JsonObject> = currencyRest.getExchangeRates(BuildConfig.APP_ID)
        return if (response.isSuccessful) {
            var exchangeRates = GsonConvertorHelper.appGsonConverter(
                ExchangeRatesResponse::class.java,
                response.body()
            )
            BaseResponse(exchangeRates, response.code())
        } else if (response.code() == 429) {
            BaseResponse(null, response.code(), response.message())
        } else {
            BaseResponse(null, response.code(), response.errorBody()?.toString())
        }
    }
}