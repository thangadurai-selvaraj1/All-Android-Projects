package com.alvin.hiltsample.ui.activity.main

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.alvin.hiltsample.R
import com.alvin.hiltsample.extenstions.showToastMessage
import com.alvin.hiltsample.local.dataStore.DataStore
import com.alvin.hiltsample.network.ResourceState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var dataStore: DataStore


    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // launchActivity<DataStoreActivity> { }

        // mainViewModel = MainViewModel(LoginFlowRepository())

        btn.setOnClickListener {
            //startActivity(Intent(this, DataStoreActivity::class.java))
            //mainViewModel.show()
            mainViewModel.login()
            showToastMessage(context, "hai")
        }
        observer()
    }

    fun observer() {
        mainViewModel.apiResponse.observe(this, {
            when (it) {
                is ResourceState.Success -> {
                    showToastMessage(context, it.response.data)
                    it.response.data
                }
                is ResourceState.GenericError -> {

                }
                is ResourceState.Loading -> {

                }
                is ResourceState.GenericErrors -> {

                }
            }
        })
    }
}