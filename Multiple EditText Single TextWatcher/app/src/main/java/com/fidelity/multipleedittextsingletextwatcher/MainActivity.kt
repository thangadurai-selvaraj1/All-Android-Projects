package com.fidelity.multipleedittextsingletextwatcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /// Create the view model
        val mainViewModel = MainViewModel()

        /// Data-Binding
        val binding = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_main)
        binding.setVariable(BR.mainViewModel, mainViewModel)

    }
}
