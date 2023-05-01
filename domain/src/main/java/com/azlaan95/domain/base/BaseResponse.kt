package com.azlaan95.domain.base

data class BaseResponse<T>(val response: T?=null, val code: Int, val message: String?=null)

