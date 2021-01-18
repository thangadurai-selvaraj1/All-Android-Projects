package com.alvin.hiltsample.network

import android.content.Context
import com.alvin.hiltsample.local.dataStore.DataStore
import com.alvin.hiltsample.network.constants.ApiConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Singleton


@Singleton
class AuthInterceptor(private val dataStore: DataStore, val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val requestBuilder: Request.Builder = request.newBuilder()
        if (request.header(ApiConstants.AUTH) == null) {
            CoroutineScope(Dispatchers.IO).launch {
                dataStore.getValue(DataStore.STRING, DataStore.AUTH_TOKEN)
                    .collect { value ->
                        value?.let { token ->
                            requestBuilder.addHeader(
                                ApiConstants.BASE_URL, token.toString()
                            )
                        }
                    }
            }
        }
        return chain.proceed(requestBuilder.build())
    }


}