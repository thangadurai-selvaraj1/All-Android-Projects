package com.alvin.dynamicsecctionrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
import android.content.Context
import android.graphics.*
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.ItemTouchHelper


class Adapter(var context: Context, var listOfData: ArrayList<Model>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mSectionView = 0
    private val mContentView = 1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == mSectionView) {
            return SectionHeaderViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.item_header,
                    parent,
                    false
                )
            )
        } else {
            return ItemViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.item_child,
                    parent,
                    false
                ), context
            )
        }
    }

    override fun getItemCount(): Int {
        return listOfData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (mSectionView == getItemViewType(position)) {
            val sectionHeaderViewHolder = holder as SectionHeaderViewHolder
            val sectionItem = listOfData[position]
            sectionHeaderViewHolder.headerTitle.setText(sectionItem.name)
        } else {

            val itemViewHolder = holder as ItemViewHolder
            val currentUser = listOfData.get(position)
            itemViewHolder.nameChild.setText(currentUser.name)

            itemViewHolder.editBtn.setOnClickListener(View.OnClickListener {
                Toast.makeText(context, "edit clicked", Toast.LENGTH_SHORT).show()
            })
            itemViewHolder.deleteBtn.setOnClickListener(View.OnClickListener {
                Toast.makeText(context, "Delete clicked", Toast.LENGTH_SHORT).show()
            })

        }
    }

    override fun getItemViewType(position: Int): Int {
        if (listOfData[position].isSection) {
            return mSectionView
        } else {
            return mContentView
        }
    }


    class SectionHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val headerTitle: TextView = itemView.findViewById(R.id.tv_header)


    }

    class ItemViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {
        var nameChild: TextView
        var editBtn: ImageButton
        var deleteBtn: ImageButton

        init {
            nameChild = itemView.findViewById(R.id.tv_child)
            editBtn = itemView.findViewById(R.id.edit_button)
            deleteBtn = itemView.findViewById(R.id.delete_button)
        }
    }


    /*   internal class SwipeHandler(private val adapter: Adapter, var context: Context) :
           ItemTouchHelper.Callback() {
           override fun getMovementFlags(
               recyclerView: RecyclerView,
               viewHolder: RecyclerView.ViewHolder
           ): Int {
               return makeMovementFlags(0, ItemTouchHelper.LEFT)
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
                       val itemViews = viewHolder.itemView
                       val height = itemViews.bottom.toFloat() - itemViews.top.toFloat()
                       val width = height / 5
                       viewHolder.itemView.translationX = dX / 5

                       paint.setColor(Color.parseColor("#D32F2F"))
                       val background = RectF(
                           itemViews.right.toFloat() + dX / 5,
                           itemViews.top.toFloat(),
                           itemViews.right.toFloat(),
                           itemViews.bottom.toFloat()
                       )
                       c.drawRect(background, paint)
                       icon = context.resources.getDrawable(R.drawable.ic_launcher_background)
                           .toBitmap()

                       val icon_dest = RectF(
                           itemViews.right + dX / 7,
                           itemViews.top.toFloat() + width,
                           itemViews.right.toFloat() + dX / 20,
                           itemViews.bottom.toFloat() - width
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

       }*/

}