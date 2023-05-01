package com.azl.currencyconvertorapp.screens.exchange_rates.ui_event

sealed class UIEvent {
    data class CurrencySelected(val currency: String) : UIEvent()
    data class AmountEntered(val amount: String) : UIEvent()
    data class DropdownOpen(val opened: Boolean) : UIEvent()
    data class GetPersisterData(val data: String?) : UIEvent()
}