package com.azlaan95.domain

import com.azlaan95.data.di.DataModule
import com.azlaan95.domain.base.GsonConvertorHelper
import com.azlaan95.domain.entities.exchange_rates.ExchangeRatesResponse
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test


class DomainUnitTest {
    @Test
    fun exchangeModelConverterTest() {
        runBlocking {
            launch {
                var response =
                    DataModule.getNetworkModule().getCurrencyRests(BuildConfig.BASE_URL)
                        .getExchangeRates(BuildConfig.APP_ID)

                var exchangeRates = GsonConvertorHelper.appGsonConverter(
                    ExchangeRatesResponse::class.java,
                    response.body()
                )

                print(exchangeRates?.rates)
                assert(exchangeRates != null)
            }
        }
    }

}


