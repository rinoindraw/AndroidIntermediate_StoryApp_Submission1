package com.rinoindraw.storybismillah.database.auth

data class AuthBody(
    val name: String,
    val email: String,
    val password: String
)