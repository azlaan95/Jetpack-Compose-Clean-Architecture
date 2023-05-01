package com.azlaan95.domain.base

data class ErrorResponse(
    val description: String?,
    val error: Boolean?,
    val message: String?,
    val status: Int?
)