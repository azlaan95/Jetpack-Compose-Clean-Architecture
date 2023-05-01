package com.azl.currencyconvertorapp.screens.exchange_rates

import AppDropdownMenu
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.azl.currencyconvertorapp.R
import com.azl.currencyconvertorapp.platform_dependents.AppDataStoreImpl
import com.azl.currencyconvertorapp.screens.exchange_rates.ui_event.UIEvent
import com.azl.currencyconvertorapp.screens.exchange_rates.viewModel.ConversionViewModel


@Composable
fun ConversionScreen() {
    val context = LocalContext.current
    val conversionViewModel = viewModel { ConversionViewModel(AppDataStoreImpl(context = context)) }
    val state = conversionViewModel.uiState.value
    LaunchedEffect(key1 = context, key2 = context) {
        conversionViewModel.messages.collect {
            Toast
                .makeText(context, it, Toast.LENGTH_SHORT)
                .show()
        }

    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        context.resources.getString(R.string.app_name),
                        color = Color.White
                    )
                },
                backgroundColor = Color(0xff0f9d58),
                contentColor = Color.White,
                elevation = 12.dp
            )
        },
        content = {
            Column(
                Modifier
                    .padding(it)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    label = { Text(context.resources.getString(R.string.insert_currency_label)) },
                    placeholder = { Text(context.resources.getString(R.string.insert_currency)) },
                    value = state.enteredAmount ?: "",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    onValueChange = {
                        conversionViewModel.onEvent(UIEvent.AmountEntered(it))
                    },
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    AppDropdownMenu(
                        label = context.resources.getString(R.string.select_currency),
                        items = state.currencies ?: arrayOf(),
                        selectedIndex = state.currencies?.indexOf(state.currencySelected ?: "")
                            ?: -1,
                        onItemSelected = { index, curren ->
                            conversionViewModel.onEvent(
                                UIEvent.CurrencySelected(
                                    curren
                                )
                            )
                        },
                    )
                }
                Box {
                    if (state.currencySelected != null) {
                        LazyColumn {
                            // on below line we are populating
                            // items for listview.
                            items(state.currencies?.size ?: 0) { index ->
                                var currency: String = state.currencies?.get(index).toString()
                                var currencyValue: Double = state.data?.rates?.get(currency) ?: 0.0
                                var selectedCurrencyValue: Double =
                                    state.data?.rates?.get(state.currencySelected) ?: 0.0
                                var outputValueOfCurrency = currencyValue / selectedCurrencyValue;
                                CurrencyGridItem(
                                    currency,
                                    outputValueOfCurrency * (state.enteredAmount?.toDouble()
                                        ?: 0.0).toDouble()
                                )
                            }
                        }
                    }
                }

            }

        }
    )
}

@Composable
fun CurrencyGridItem(currency: String, currencyValue: Double) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
                .clickable(onClick = { })
        ) {
            Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = currency,
                    textAlign = TextAlign.Center,
                    style = typography.h3,
                )
            }
            Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "$currencyValue",
                    textAlign = TextAlign.Center,
                    style = typography.h6,
                )
            }
        }
    }
}


