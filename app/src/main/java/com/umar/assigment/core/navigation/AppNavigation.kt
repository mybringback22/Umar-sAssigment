package com.umar.assigment.core.navigation

import com.umar.assigment.domain.model.Problem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("LoginScreenRoute")
object LoginScreenRoute

@Serializable
@SerialName("LandingScreenRoute")
data class  LandingScreenRoute(var userName : String)

@Serializable
@SerialName("DetailScreenRoute")
data class DetailScreenRoute(var problem: Problem)