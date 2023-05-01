package com.azlaan95.domain.base

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonSyntaxException


object GsonConvertorHelper {
    fun <T> appGsonConverter(type: Class<T>, json: JsonObject?): T? {
        return try {
            var myObject = Gson().fromJson(json, type)
            myObject
        } catch (e: Exception) {
            null
        }
    }

    fun <T> jsonStringToModel(jsonString: String, classType: Class<T>): T? {
        return try {
            Gson().fromJson(jsonString, classType)
        } catch (e: JsonSyntaxException) {
            null;
        }
    }

}