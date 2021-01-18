package com.alvin.hiltsample.network

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import retrofit2.Response


/**
 * Abstract Base Data source class with error handling
 */
abstract class BaseDataSource {


    fun <T> getResult(response: Response<T>, context: Context): ResourceState<T> {
        try {
            var apiError: ApiError? = null

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return ResourceState.Success(body)
                }
            } else {
                val errorBody = response.errorBody()
                val error = errorBody?.string()
                apiError = Gson().fromJson(error, ApiError::class.java)
            }

            if (response.code() == 401) {
                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed({
                    apiError?.message?.let {
                        /*context.launchActivity<AlertDialogue> {
                            putExtra(AlertDialogue.BODY, it)
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        }*/
                    }
                }, 100L)
            }

            return errorCheck(CustomException(response.code(), apiError))

        } catch (e: Exception) {
            return errorCheck(CustomException(1000, e.toString()))
        }
    }

    private fun <T> errorCheck(customException: CustomException): ResourceState<T> {
        return ResourceState.GenericError(customException.code, customException.apiError)
    }

    /* protected suspend fun <T> getResult(call: suspend () -> Response<T>): ResourceState<T> {
         try {
             val response = call()
             if (response.isSuccessful) {
                 val body = response.body()
                 if (body != null) return ResourceState.success(body)
             }
             return errorCheck(CustomException(response.code(), ""))
         } catch (e: Exception) {
             return errorCheck(CustomException(1000, e.toString()))
         }
     }

     private fun <T> errorCheck(customException: CustomException): ResourceState<T> {
         return ResourceState.error(customException)
     }*/

}

