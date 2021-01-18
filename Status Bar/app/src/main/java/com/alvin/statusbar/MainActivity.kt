package com.alvin.statusbar

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sample)

        val mTopToolbar = findViewById<Toolbar>(R.id.tb)
        setSupportActionBar(mTopToolbar)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> {
                Toast.makeText(this@MainActivity, "Action Refresh", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_rotate -> {
                Toast.makeText(this@MainActivity, "Action Rotate", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_face -> {
                Toast.makeText(this@MainActivity, "Action Face", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}