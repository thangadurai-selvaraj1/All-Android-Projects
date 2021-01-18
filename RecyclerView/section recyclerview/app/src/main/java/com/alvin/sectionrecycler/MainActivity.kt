package com.alvin.sectionrecycler


import android.graphics.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import android.graphics.RectF
import android.graphics.BitmapFactory
import android.graphics.Color.parseColor
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import com.alvin.sectionrecycler.SectionRecyclerAdapter.SectionViewHolder

import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class MainActivity : AppCompatActivity() {
    var swipeController: SwipeController? = null
    lateinit var adapter: SectionRecyclerAdapter
    lateinit var rv: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv = findViewById<RecyclerView>(R.id.rv)
        val rv1 = findViewById<RecyclerView>(R.id.item_recycler_view)

        val list = ArrayList<String>()
        list.add("One")
        list.add("Two")
        list.add("Three")
        list.add("Four")
        list.add("Five")

        val list1 = ArrayList<String>()
        list1.add("Ohjne")
        list1.add("Tcdswo")
        list1.add("Thasdcree")
        list1.add("Fosadcur")
        list1.add("Fiaswwve")

        val listName = ArrayList<String>()
        listName.add("Admin")
        listName.add("Cashier")




        adapter = SectionRecyclerAdapter(this, list, list1, listName)
        rv.setAdapter(adapter)

        //   initSwipe()




           swipeController = SwipeController(object : SwipeControllerActions {
               override fun onRightClicked(position: Int) {
                   //adapter.players.remove(position)
                   adapter.notifyItemRemoved(position)
                   adapter.notifyItemRangeChanged(position, adapter.getItemCount())
               }

               override fun onLeftClicked(position: Int) {

               }
           })

           val itemTouchhelper = ItemTouchHelper(swipeController!!)
           itemTouchhelper.attachToRecyclerView(rv1)

         /*  rv.addItemDecoration(object : RecyclerView.ItemDecoration() {
               override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                   swipeController!!.onDraw(c)
               }
           })*/

        /*  val simpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
              override fun onMove(
                  recyclerView: RecyclerView,
                  viewHolder: RecyclerView.ViewHolder,
                  target: RecyclerView.ViewHolder
              ): Boolean {
                  return false
              }

              override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                  val position = viewHolder.adapterPosition
                  if (direction == ItemTouchHelper.LEFT) {
                      adapter.removeItem(position)
                  }
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
                      var paint = Paint()
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
                          icon = BitmapFactory.decodeResource(
                              resources,
                              R.drawable.ic_launcher_background
                          )
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
          }

          val itemTouchHelper = ItemTouchHelper(simpleCallback)

          itemTouchHelper.attachToRecyclerView(rv)
  */
    }


   /* private fun initSwipe() {
        val simpleItemTouchCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                if (direction == ItemTouchHelper.LEFT) {
                    adapter.removeItem(position)
                } else {
                    // removeView()
                    *//* edit_position = position
                     alertDialog.setTitle("Edit Country")
                     et_country.setText(countries.get(position))
                  *//*
                }
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

                val icon: Bitmap
                val p = Paint()
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                    val itemView = viewHolder.itemView
                    val height = itemView.bottom - itemView.top
                    val width = height / 3

                    if (dX > 0) {

                        p.setColor(Color.parseColor("#388E3C"))
                        val background = RectF(
                            itemView.left.toFloat(),
                            itemView.top.toFloat(),
                            dX,
                            itemView.bottom.toFloat()
                        )
                        c.drawRect(background, p)
                        icon = BitmapFactory.decodeResource(
                            resources,
                            R.drawable.ic_launcher_background
                        )
                        val icon_dest = RectF(
                            itemView.left.toFloat() + width,
                            itemView.top.toFloat() + width,
                            itemView.left.toFloat() + 2 * width,
                            itemView.bottom.toFloat() - width
                        )
                        c.drawBitmap(icon, null, icon_dest, p)
                    } else {
                        p.setColor(Color.parseColor("#D32F2F"))
                        val background = RectF(
                            itemView.right.toFloat() + dX,
                            itemView.top.toFloat(),
                            itemView.right.toFloat(),
                            itemView.bottom.toFloat()
                        )
                        c.drawRect(background, p)
                        icon = BitmapFactory.decodeResource(
                            resources,
                            R.drawable.ic_launcher_background
                        )
                        val icon_dest = RectF(
                            itemView.right.toFloat() - 2 * width,
                            itemView.top.toFloat() + width,
                            itemView.right.toFloat() - width,
                            itemView.bottom.toFloat() - width
                        )
                        c.drawBitmap(icon, null, icon_dest, p)
                    }
                }
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
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(rv)
    }*/

    /*private fun removeView() {
        if (view.getParent() != null) {
            (view.getParent() as ViewGroup).removeView(view)
        }
    }*/


    /*  fun onClick(v: View) {

          when (v.getId()) {
              R.id.fab -> {
                  removeView()
                  add = true
                  alertDialog.setTitle("Add Country")
                  et_country.setText("")
                  alertDialog.show()
              }
          }
      }*/

}
