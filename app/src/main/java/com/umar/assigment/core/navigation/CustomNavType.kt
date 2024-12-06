package com.umar.assigment.core.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.umar.assigment.domain.model.Problem
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {
    val ProblemType = object : NavType<Problem>(isNullableAllowed = false){
        override fun get(bundle: Bundle, key: String): Problem? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): Problem {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: Problem): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: Problem) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}