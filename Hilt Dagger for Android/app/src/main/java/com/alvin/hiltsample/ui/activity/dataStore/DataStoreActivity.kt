package com.alvin.hiltsample.ui.activity.dataStore

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.alvin.hiltsample.R
import com.alvin.hiltsample.local.dataStore.DataStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_data_store.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DataStoreActivity : AppCompatActivity() {
    @Inject
    lateinit var context: Context

    @Inject
    lateinit var dataStore: DataStore

    private var isBool = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_store)

        button.setOnClickListener {
            lifecycleScope.launch {
                dataStore.setValue(
                    DataStore.INT,
                    DataStore.SAVE_INT,
                    editTextTextPersonName.text.toString().toInt()
                )
            }
        }

        button1.setOnClickListener {
            lifecycleScope.launch {
                dataStore.setValue(
                    DataStore.STRING,
                    DataStore.SAVE_STRING,
                    editTextTextPersonName1.text.toString()
                )
            }

        }

        button2.setOnClickListener {

            isBool = !isBool
            lifecycleScope.launch {
                dataStore.setValue(
                    DataStore.BOOLEAN,
                    DataStore.SAVE_BOOL,
                    isBool
                )
            }

        }


        observeUiPreferences()
    }

    private fun observeUiPreferences() {
        dataStore.getValue(DataStore.INT, DataStore.SAVE_INT).asLiveData()
            .observe(this, {
                it?.let {
                    textView.text = it.toString() // as String
                }

            })

        dataStore.getValue(DataStore.STRING, DataStore.SAVE_STRING).asLiveData()
            .observe(this, {
                it?.let {
                    textView1.text = it.toString() // as String
                }
            })

        dataStore.getValue(DataStore.BOOLEAN, DataStore.SAVE_BOOL).asLiveData()
            .observe(this, {
                it?.let {
                    textView2.text = it.toString() // as String
                }

            })
    }
}