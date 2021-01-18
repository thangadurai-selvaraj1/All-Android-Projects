package com.alvin.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import java.util.*


/*
* Live Data
*
* MutableLiveData
*
* Transformation-> Map
*
* Mediator Live Data
*
* SetValue and  getValue
*
* Transformation-> Switch Map
*
* */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainViewModel().putData(random())

        MainViewModel().liveData.observe(this, Observer {result ->
            print(
                result
            )
            findViewById<TextView>(R.id.tv).text=result
        }
        )

        findViewById<Button>(R.id.btn).setOnClickListener(View.OnClickListener {
            MainViewModel().putData(random())
        })



    }





    private fun random():String{
        val random = Random()
        return random.nextInt(5).toString()
    }
}
