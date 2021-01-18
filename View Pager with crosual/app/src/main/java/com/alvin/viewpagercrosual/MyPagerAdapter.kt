package com.alvin.viewpagercrosual

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter


class MyPagerAdapter(context: Context, listItems: IntArray, adapterType: Int) : PagerAdapter() {
    private var context: Context
    var listItems: IntArray
    var adapterType: Int
   override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_cover, null)
        try {
            val linMain = view.findViewById(R.id.linMain) as LinearLayout
            linMain.tag = position
            when (adapterType) {
                MainActivity.ADAPTER_TYPE_TOP -> linMain.setBackgroundResource(R.drawable.ic_launcher_background)
                MainActivity.ADAPTER_TYPE_BOTTOM -> linMain.setBackgroundResource(0)
            }
            container.addView(view)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }
   override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return listItems.size
    }

    init {
        this.context = context
        this.listItems = listItems
        this.adapterType = adapterType
    }
}