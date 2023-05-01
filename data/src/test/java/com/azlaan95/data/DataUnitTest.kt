package com.azlaan95.data

import com.azlaan95.data.di.DataModule
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class DataUnitTest {
    @Test
    fun myRepoTest() {
        runBlocking {
            launch {
                var response =
                    DataModule.getNetworkModule().getCurrencyRests("https://openexchangerates.org/")
                        .getExchangeRates("c8741d9df4464e95835308770b332215")
                print(response.body())
                assert(response.isSuccessful)
            }
        }
    }
}

