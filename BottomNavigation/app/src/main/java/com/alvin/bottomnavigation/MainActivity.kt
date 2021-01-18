package com.alvin.bottomnavigation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.alvin.bottomnavigation.ui.dashboard.DashboardFragment
import com.alvin.bottomnavigation.ui.home.HomeFragment
import com.alvin.bottomnavigation.ui.notifications.NotificationsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    lateinit var navView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navView = findViewById(R.id.bottom_navigation_view)

        navView.setOnNavigationItemSelectedListener(this)


        navView.setSelectedItemId(R.id.navigation_home)

    /*    navView.menu.getItem(i).icon  =
            AnimatedVectorDrawableCompat.create(this, R.drawable.ic_settings_active_avd)
        val anim = bottomNavigationId.menu.getItem(i).icon as Animatable
        anim.start()
*/

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {

                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .replace(R.id.container, HomeFragment()).commit()
                return true
            }

            R.id.navigation_dashboard -> {

                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .replace(R.id.container, DashboardFragment()).commit()
                return true
            }


        }

        return false
    }


}
