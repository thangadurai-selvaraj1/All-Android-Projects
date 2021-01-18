package com.alvin.hiltsample.ui.activity.main

import android.content.Context
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alvin.hiltsample.R
import com.alvin.hiltsample.extenstions.showToastMessage
import com.alvin.hiltsample.network.ResourceState
import com.alvin.hiltsample.repo.network.LoginFlowRepository
import com.alvin.hiltsample.utils.NetworkHelper
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val loginFlowRepository: LoginFlowRepository,
    val context: Context
) :
    ViewModel() {

    var apiResponse = loginFlowRepository.apiResponse

    fun login() {
        viewModelScope.launch {
            kotlin.runCatching {

                if (NetworkHelper.isNetworkConnected(context)) {
                    apiResponse.postValue(ResourceState.Loading)
                    loginFlowRepository.login()
                } else {
                    Toast.makeText(
                        context,
                        context.getString(R.string.app_name),
                        Toast.LENGTH_SHORT
                    ).show()
                }
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

    fun show(){
        showToastMessage(context,"hai")
    }
}