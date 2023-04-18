package com.rinoindraw.storybismillah.database.model

import com.google.gson.annotations.SerializedName
import com.rinoindraw.storybismillah.database.model.User

data class AuthResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("loginResult")
    val loginResult: User
)