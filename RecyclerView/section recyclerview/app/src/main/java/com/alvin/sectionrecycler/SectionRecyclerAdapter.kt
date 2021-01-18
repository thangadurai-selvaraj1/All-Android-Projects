package com.alvin.sectionrecycler

import android.content.Context
import android.graphics.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import android.graphics.Color.parseColor
import com.alvin.sectionrecycler.SwipeController.Companion.buttonWidth


open class SectionRecyclerAdapter(
    private val context: Context,

    private val firstList: ArrayList<String>,
    private val secondList: ArrayList<String>,
    private val heading: ArrayList<String>
) : RecyclerView.Adapter<SectionRecyclerAdapter.SectionViewHolder>() {

    lateinit var adapter: ItemRecyclerViewAdapter

    open inner class SectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sectionLabel: TextView
        val itemRecyclerView: RecyclerView

        init {
            sectionLabel = itemView.findViewById(R.id.section_label)
            itemRecyclerView = itemView.findViewById(R.id.item_recycler_view)

           /* ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                    Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show()
                }

                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {

                    try {

                        val icon: Bitmap
                        val paint = Paint()
                        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                            val itemView = viewHolder.itemView
                            val height = itemView.bottom.toFloat() - itemView.top.toFloat()
                            val width = height / 5
                            viewHolder.itemView.translationX = dX / 5

                            paint.setColor(Color.parseColor("#D32F2F"))
                            val background = RectF(
                                itemView.right.toFloat() + dX / 5,
                                itemView.top.toFloat(),
                                itemView.right.toFloat(),
                                itemView.bottom.toFloat()
                            )
                            c.drawRect(background, paint)
                            icon = context.resources.getDrawable(R.drawable.ic_launcher_background)
                                .toBitmap()
                            val icon_dest = RectF(
                                itemView.right + dX / 7,
                                itemView.top.toFloat() + width,
                                itemView.right.toFloat() + dX / 20,
                                itemView.bottom.toFloat() - width
                            )
                            c.drawBitmap(icon, null, icon_dest, paint)
                        } else {
                            super.onChildDraw(
                                c,
                                recyclerView,
                                viewHolder,
                                dX,
                                dY,
                                actionState,
                                isCurrentlyActive
                            )
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }

            }).attachToRecyclerView(itemRecyclerView)*/


         /* var  swipeController = SwipeController(object : SwipeControllerActions {
                override fun onRightClicked(position: Int) {
                  //  adapter.removeAt(position)
               //     mAdapter.notifyItemRemoved(position)
                //    mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount())
                }
            })

            val itemTouchhelper = ItemTouchHelper(swipeController)
            itemTouchhelper.attachToRecyclerView(itemRecyclerView)

            itemRecyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                    swipeController.onDraw(c)
                }
            })*/

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.section_custom_row, parent, false)
        return SectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val sectionModel = heading[position]
        holder.sectionLabel.setText(sectionModel)
        //recycler view for items
        holder.itemRecyclerView.isNestedScrollingEnabled = false

        if (position == 0) {
            adapter = ItemRecyclerViewAdapter(context, firstList)
            holder.itemRecyclerView.adapter = adapter
        } else if (position == 1) {
            adapter = ItemRecyclerViewAdapter(context, secondList)
            holder.itemRecyclerView.adapter = adapter
        }

        holder.itemRecyclerView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                Toast.makeText(
                    context,
                    "You clicked on Show All of : " + sectionModel,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        holder.sectionLabel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                Toast.makeText(
                    context,
                    "You clicked on Show All of : " + sectionModel,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })


    }




    override fun getItemCount(): Int {
        return heading.size
    }

    /* fun addItem(country: String) {
         sectionModelArrayList.add(country)
         notifyItemInserted(sectionModelArrayList.size)
     }

     fun removeItem(position: Int) {
         sectionModelArrayList.removeAt(position)
         notifyItemRemoved(position)
         notifyItemRangeChanged(position, sectionModelArrayList.size)
     }*/

}