package com.azl.currencyconvertorapp.screens.exchange_rates.ui_state

import com.azlaan95.domain.entities.exchange_rates.ExchangeRatesResponse

data class ConversionUiState(
    val loading: Boolean = false,
    val data: ExchangeRatesResponse? = null,
    val exception: String? = null,
    val dropDownOpen: Boolean = false,
    val enteredAmount: String? = "1",
    val currencySelected: String? = null,
    val currencies: Array<String>? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ConversionUiState

        if (loading != other.loading) return false
        if (data != other.data) return false
        if (exception != other.exception) return false
        if (dropDownOpen != other.dropDownOpen) return false
        if (enteredAmount != other.enteredAmount) return false
        if (currencySelected != other.currencySelected) return false
        if (currencies != null) {
            if (other.currencies == null) return false
            if (!currencies.contentEquals(other.currencies)) return false
        } else if (other.currencies != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = loading.hashCode()
        result = 31 * result + (data?.hashCode() ?: 0)
        result = 31 * result + (exception?.hashCode() ?: 0)
        result = 31 * result + dropDownOpen.hashCode()
        result = 31 * result + (enteredAmount?.hashCode() ?: 0)
        result = 31 * result + (currencySelected?.hashCode() ?: 0)
        result = 31 * result + (currencies?.contentHashCode() ?: 0)
        return result
    }
}