package com.alvin.hiltsample.network

import com.google.gson.annotations.SerializedName

class ApiError(
    var message: String?,
    @SerializedName("error")
    var error: Map<String, String>? = null
)