package com.alvin.drawingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alvin.drawingapp.draw.DrawView
import com.alvin.drawingapp.draw.DrawingView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
