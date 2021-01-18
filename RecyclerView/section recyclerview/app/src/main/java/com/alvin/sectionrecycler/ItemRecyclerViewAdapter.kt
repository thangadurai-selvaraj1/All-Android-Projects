package com.alvin.sectionrecycler


import android.content.Context
import android.media.MediaRouter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import java.nio.file.Files.size
import java.nio.file.Files.delete






class ItemRecyclerViewAdapter(
    private val context: Context,
    private val arrayList: ArrayList<String>
) : RecyclerView.Adapter<ItemRecyclerViewAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemLabel: TextView

        init {
            itemLabel = itemView.findViewById(R.id.item_label)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_costom_row, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemLabel.text = arrayList[position]

        holder.itemLabel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                Toast.makeText(
                    context,
                    "You clicked on Show All of : " +  holder.itemLabel.text,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

    }


    override fun getItemCount(): Int {
        return arrayList.size
    }





}