package com.alvin.viewpagercrosual

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {
    private var viewpagerTop: ViewPager? = null
    companion object{
        val ADAPTER_TYPE_TOP = 1
        val ADAPTER_TYPE_BOTTOM = 2
        val EXTRA_IMAGE = "image"
        val EXTRA_TRANSITION_IMAGE = "image"
    }

    private val listItems = intArrayOf(1,2,3,4,5,6,7)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        setupViewPager()
    }


    private fun init() {
        viewpagerTop = findViewById<ViewPager>(R.id.viewpagerTop)
        viewpagerTop?.clipChildren = false
        viewpagerTop?.pageMargin = resources.getDimensionPixelOffset(R.dimen.pager_margin)
        viewpagerTop?.offscreenPageLimit = 3
        viewpagerTop?.setPageTransformer(false, CarouselEffectTransformer(this)) // Set transformer
    }

    private fun setupViewPager() {
        // Set Top ViewPager Adapter
        val adapter = MyPagerAdapter(this, listItems, ADAPTER_TYPE_TOP)
        viewpagerTop!!.adapter = adapter

        viewpagerTop!!.addOnPageChangeListener(object :
                ViewPager.OnPageChangeListener {
            private var index = 0
            override fun onPageSelected(position: Int) {
                index = position
            }

            override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
            ) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    fun clickEvent(view: View) {
        when (view.id) {
            R.id.linMain -> if (view.tag != null) {
                val position = view.tag.toString().toInt()
                Toast.makeText(applicationContext, "Position: $position", Toast.LENGTH_LONG).show()
            }
        }
    }



}