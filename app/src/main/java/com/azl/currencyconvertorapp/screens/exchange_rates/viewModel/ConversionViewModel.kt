package com.azl.currencyconvertorapp.screens.exchange_rates.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azl.currencyconvertorapp.platform_dependents.AppDataStoreImpl.Companion.EXCHANGE_RESPONSE
import com.azl.currencyconvertorapp.screens.exchange_rates.ui_event.UIEvent
import com.azl.currencyconvertorapp.screens.exchange_rates.ui_state.ConversionUiState
import com.azlaan95.domain.base.BaseResponse
import com.azlaan95.domain.base.GsonConvertorHelper
import com.azlaan95.domain.di.DomainModule
import com.azlaan95.domain.entities.exchange_rates.ExchangeRatesResponse
import com.azlaan95.domain.platform_dependent.AppDataStore
import com.azlaan95.domain.usecase.ConversionUseCase
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch


class ConversionViewModel(
    private val appDataStore: AppDataStore,
    private val useCase: ConversionUseCase = DomainModule.getConversionUseCase(),
) : ViewModel() {
    private var _uiState = mutableStateOf(ConversionUiState())
    val uiState: State<ConversionUiState> = _uiState
    val messages = MutableSharedFlow<String>()

    init {
        viewModelScope.launch {
            appDataStore.getString<Flow<String?>>(EXCHANGE_RESPONSE).collect {
                onEvent(UIEvent.GetPersisterData(it))
            }
        }
    }

    fun onEvent(event: UIEvent) {
        when (event) {
            is UIEvent.AmountEntered -> {
                if (!event.amount.isNullOrEmpty())
                    _uiState.value = _uiState.value.copy(
                        enteredAmount = event.amount
                    )
                else
                    _uiState.value = _uiState.value.copy(
                        enteredAmount = null
                    )
            }

            is UIEvent.CurrencySelected -> {
                _uiState.value = _uiState.value.copy(
                    currencySelected = event.currency
                )
            }

            is UIEvent.DropdownOpen -> {
                _uiState.value = _uiState.value.copy(
                    dropDownOpen = event.opened
                )
            }

            is UIEvent.GetPersisterData -> {
                event.data?.let { persistedData ->
                    var exchangeResponse: ExchangeRatesResponse? =
                        GsonConvertorHelper.jsonStringToModel(
                            persistedData,
                            ExchangeRatesResponse::class.java
                        )
                    exchangeResponse?.let { exRate ->
                        _uiState.value = _uiState.value.copy(
                            data = exRate,
                            currencies = exRate.rates?.keys?.toTypedArray(),
                            currencySelected = exRate.base
                        )
                    } ?: run {
                        fetchExchangeRates()
                    }
                } ?: run {
                    fetchExchangeRates()
                }

            }
        }
    }

    private fun fetchExchangeRates() {
        _uiState.value = _uiState.value.copy(
            loading = true
        )
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val baseResponse: BaseResponse<ExchangeRatesResponse> =
                    useCase.fetchExchangeRates()
                if (baseResponse.response != null) {
                    appDataStore.saveString(Gson().toJson(baseResponse.response), EXCHANGE_RESPONSE)
                    _uiState.value = _uiState.value.copy(
                        data = baseResponse.response,
                        currencies = baseResponse.response?.rates?.keys?.toTypedArray(),
                        currencySelected = baseResponse.response?.base
                    )
                } else {
                    messages.emit(baseResponse.message ?: "SomeThingWentWrong")
                }
            } catch (ex: Exception) {
                messages.emit(
                    ex.message ?: ex.cause?.message ?: ex.localizedMessage
                    ?: "Unable To Get Exception"
                )
            }
        }
    }
}