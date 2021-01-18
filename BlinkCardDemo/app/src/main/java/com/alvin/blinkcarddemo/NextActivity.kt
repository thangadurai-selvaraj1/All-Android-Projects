package com.alvin.blinkcarddemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class NextActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        findViewById<Button>(R.id.button).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).putExtra(MainActivity.KEY, KEY))
        }
    }

    companion object {
        const val KEY =
            "sRwAAAAXY29tLmFsdmluLmJsaW5rY2FyZGRlbW9AIm6VPzExvpWGxj9GbzZ/zbFLWkyg3JBQRzWipUCNbCdx0Hejy5qZX30fEUdL/YHq2Rn3iCOppiGtWVgDV80uqUwWe3tVd24biKCDh6O95Wi8W1WHWJaYfqvZ+UE4fACaywJmKK8z6yzfYCPdDC4qfqVESOS+kwDFSKRHiByBNyKx3WUtyrYjr+AecFeyBAz7I4Svw9Weel/CB3kweJfg4yOzPlkt5z6fB+SgL2rs"
    }
}