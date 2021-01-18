package com.alvin.veileffect

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.skydoves.androidveil.VeilLayout


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myLayout = findViewById<VeilLayout>(R.id.veilLayout)
        myLayout.layout = R.layout.veil_layout
        showAndHideVeilEffect(myLayout,R.layout.layout_veil)

        val handler = Handler()
        handler.postDelayed({
            showAndHideVeilEffect(myLayout, R.layout.layout_veil)
        }, 5000)
    }


    private fun showAndHideVeilEffect(myLayout: VeilLayout, layoutVeil: Int=R.layout.veil_layout) {
        if (myLayout.isVeiled) {
            myLayout.unVeil()
            myLayout.visibility = View.GONE
        } else {
            myLayout.veil()
            myLayout.visibility = View.VISIBLE
        }
    }
}