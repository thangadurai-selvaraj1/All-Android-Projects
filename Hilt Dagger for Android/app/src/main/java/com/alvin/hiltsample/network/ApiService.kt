package com.alvin.hiltsample.network

import com.alvin.hiltsample.model.LoginResponse
import com.alvin.hiltsample.network.constants.ApiEndPoints
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
    @GET(ApiEndPoints.List)
    suspend fun get(): Response<LoginResponse>
}