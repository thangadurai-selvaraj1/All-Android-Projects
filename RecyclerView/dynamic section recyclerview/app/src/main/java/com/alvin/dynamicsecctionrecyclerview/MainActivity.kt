package com.alvin.dynamicsecctionrecyclerview

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList
import androidx.recyclerview.widget.ItemTouchHelper


class MainActivity : AppCompatActivity() {

    lateinit var rv: RecyclerView
    lateinit var mAdapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv = findViewById(R.id.rv)

        val list = ArrayList<Model>()


        list.add(Model("One", true))
        list.add(Model("A", false))
        list.add(Model("A", false))
        list.add(Model("A", false))
        list.add(Model("A", false))
        list.add(Model("A", false))

        list.add(Model("Two", true))
        list.add(Model("B", false))
        list.add(Model("B", false))
        list.add(Model("B", false))
        list.add(Model("B", false))
        list.add(Model("B", false))

        list.add(Model("Three", true))
        list.add(Model("C", false))
        list.add(Model("C", false))
        list.add(Model("C", false))
        list.add(Model("C", false))
        list.add(Model("C", false))

        list.add(Model("Four", true))
        list.add(Model("d", false))
        list.add(Model("d", false))
        list.add(Model("d", false))
        list.add(Model("d", false))
        list.add(Model("d", false))

        rv.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(this)
        rv.layoutManager = mLayoutManager
        mAdapter = Adapter(this, list)
        rv.adapter = mAdapter

    }
}
