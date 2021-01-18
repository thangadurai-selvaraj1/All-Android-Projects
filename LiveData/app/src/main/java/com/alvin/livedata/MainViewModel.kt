package com.alvin.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var liveData=MutableLiveData<String>()

    fun putData(data:String){
        liveData.value=data
    }
}