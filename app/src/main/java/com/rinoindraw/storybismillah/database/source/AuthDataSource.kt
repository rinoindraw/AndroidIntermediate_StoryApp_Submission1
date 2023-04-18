package com.rinoindraw.storybismillah.database.source

import com.rinoindraw.storybismillah.database.ApiResponse
import com.rinoindraw.storybismillah.database.auth.AuthBody
import com.rinoindraw.storybismillah.database.model.AuthResponse
import com.rinoindraw.storybismillah.database.auth.AuthService
import com.rinoindraw.storybismillah.database.auth.LoginBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthDataSource @Inject constructor(private val authService: AuthService) {

    suspend fun registerUser(authBody: AuthBody): Flow<ApiResponse<Response<AuthResponse>>> {
        return flow {
            try {
                emit(ApiResponse.Loading)
                val response = authService.registerUser(authBody)
                if (response.code() == 201) {
                    emit(ApiResponse.Success(response))
                } else if (response.code() == 400) {
                    val errorBody = JSONObject(response.errorBody()!!.string())
                    emit(ApiResponse.Error(errorBody.getString("message")))
                }
            } catch (ex: Exception) {
                emit(ApiResponse.Error(ex.message.toString()))
            }
        }
    }

    suspend fun loginUser(loginBody: LoginBody): Flow<ApiResponse<AuthResponse>> {
        return flow {
            try {
                emit(ApiResponse.Loading)
                val response = authService.loginUser(loginBody)
                if (!response.error) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Error(response.message))
                }
            } catch (ex: Exception) {
                emit(ApiResponse.Error(ex.message.toString()))
            }
        }
    }

}