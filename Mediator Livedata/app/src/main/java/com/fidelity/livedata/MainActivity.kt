package com.fidelity.livedata

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.fidelity.livedata.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.mainViewModel = MainViewModel()

          MainViewModel().zipLiveData(
              MainViewModel().live1,
              MainViewModel().live2
          ).observe(this
              , Observer {
                  print(it.first)
                  print(it.second)

                  Toast.makeText(this, "${it.first} --- ${it.second}", Toast.LENGTH_SHORT).show()
              })

      /*  MainViewModel().mid.observe(this, Observer {

            Toast.makeText(this, "$it ----", Toast.LENGTH_SHORT).show()
        })*/

        // observe()

    }


    fun observe() {
        MainViewModel().zipLiveData(
            MainViewModel().live1,
            MainViewModel().live2
        ).observe(this,
            Observer {

                print(it.first)
                print(it.second)

                Toast.makeText(this, "${it.first} --- ${it.second}", Toast.LENGTH_SHORT).show()

            })
    }
}
