package com.alvin.hiltsample.repo.network

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.alvin.hiltsample.R
import com.alvin.hiltsample.model.LoginResponse
import com.alvin.hiltsample.network.*
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class LoginFlowRepository @Inject constructor(
    private var context: Context,
    private val apiService: ApiService
) : BaseDataSource() {

    val apiResponse: MutableLiveData<ResourceState<LoginResponse>> by lazy {
        MutableLiveData<ResourceState<LoginResponse>>()
    }

    suspend fun login() {
        withContext(Dispatchers.IO) {
            kotlin.runCatching {
                apiService.get()
            }.onSuccess {
                apiResponse.postValue(getResult(it, context))
            }.onFailure {
                apiResponse.postValue(
                    ResourceState.GenericErrors(
                        -1,
                        it.message ?: context.getString(R.string.app_name)
                    )
                )

            }
        }
    }

    private fun convertErrorBody(throwable: HttpException): ApiError? {
        return throwable.response()?.errorBody()?.string()?.let {
            return Gson().fromJson(it, ApiError::class.java)
        }

    }

}