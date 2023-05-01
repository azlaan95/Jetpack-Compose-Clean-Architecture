package com.azl.currencyconvertorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import com.azl.currencyconvertorapp.platform_dependents.AppDataStoreImpl
import com.azl.currencyconvertorapp.screens.exchange_rates.ConversionScreen
import com.azl.currencyconvertorapp.screens.exchange_rates.viewModel.ConversionViewModel
import com.azl.currencyconvertorapp.ui.theme.CurrencyConvertorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*var viewModel =
            ConversionViewModel(AppDataStoreImpl(context = applicationContext))*/
        setContent {
            CurrencyConvertorAppTheme {
                Surface {
                    ConversionScreen()
                }
            }
        }
    }
}
