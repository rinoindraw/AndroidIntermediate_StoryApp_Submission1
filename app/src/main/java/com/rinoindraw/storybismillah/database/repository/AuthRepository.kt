package com.rinoindraw.storybismillah.database.repository

import com.rinoindraw.storybismillah.database.ApiResponse
import com.rinoindraw.storybismillah.database.auth.LoginBody
import com.rinoindraw.storybismillah.database.auth.AuthBody
import com.rinoindraw.storybismillah.database.source.AuthDataSource
import com.rinoindraw.storybismillah.database.model.AuthResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(private val authDataSource: AuthDataSource) {

    suspend fun registerUser(authBody: AuthBody): Flow<ApiResponse<Response<AuthResponse>>> {
        return authDataSource.registerUser(authBody).flowOn(Dispatchers.IO)
    }

    suspend fun loginUser(loginBody: LoginBody): Flow<ApiResponse<AuthResponse>> {
        return authDataSource.loginUser(loginBody).flowOn(Dispatchers.IO)
    }

}