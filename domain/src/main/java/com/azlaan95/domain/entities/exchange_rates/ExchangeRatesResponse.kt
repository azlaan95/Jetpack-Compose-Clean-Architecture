package com.azlaan95.domain.entities.exchange_rates


data class ExchangeRatesResponse(
    val base: String?,
    val disclaimer: String?,
    val license: String?,
    val rates: Map<String, Double>?,
    val timestamp: Int?
)