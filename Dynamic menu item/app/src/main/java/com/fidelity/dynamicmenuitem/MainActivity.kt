package com.fidelity.dynamicmenuitem


import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val MENU_EDIT = 0
    private val MENU_DELETE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            changeBg(R.color.red, R.drawable.ic_arrow_back_black_24dp)

        }

        btn1.setOnClickListener {
            changeBg(R.color.yellow, R.drawable.ic_arrow_back_black_24dp)


        }

        btn2.setOnClickListener {

            changeBg(R.color.colorPrimary, R.drawable.ic_arrow_back_black_24dp)
        }


    }

    private fun changeBg(color: Int, icon: Int) {

        val background = GradientDrawable()
        background.setColor(ContextCompat.getColor(this@MainActivity, color))

        background.cornerRadius = 10f

        background.setStroke(1, R.color.red)

        background.shape = GradientDrawable.OVAL

        val backgroundImage =
            ContextCompat.getDrawable(this@MainActivity, icon)

        // change image size
/*

        val bitmap = (backgroundImage as BitmapDrawable).bitmap

        val d: Drawable = BitmapDrawable(
            resources,
            Bitmap.createScaledBitmap(bitmap, 50, 50, true)
        )
*/


        val layers = arrayOf(
            background,
            backgroundImage!!
        )

        val layerDrawable = LayerDrawable(layers)
        layerDrawable.setLayerInset(0, 1, 1, 1, 1)

        layerDrawable.setLayerInset(1, 10, 10, 10, 10)


        invalidateOptionsMenu()
        val s = supportActionBar
        s?.setDisplayHomeAsUpEnabled(true)
        s?.setHomeAsUpIndicator(layerDrawable)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.clear()
        menu.add(0, MENU_EDIT, Menu.NONE, getString(R.string.menu_action_edit))
            .setIcon(R.drawable.ic_calendar).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menu.add(0, MENU_DELETE, Menu.NONE, getString(R.string.menu_action_delete))
            .setIcon(R.drawable.ic_action_delete).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)

        val background = GradientDrawable()
        background.setColor(ContextCompat.getColor(this@MainActivity, R.color.red))

        background.cornerRadius = 10f

        background.setStroke(1, R.color.red)

        background.shape = GradientDrawable.OVAL

        val backgroundImage =
            ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_action_delete)

        // change image size
/*

        val bitmap = (backgroundImage as BitmapDrawable).bitmap

        val d: Drawable = BitmapDrawable(
            resources,
            Bitmap.createScaledBitmap(bitmap, 50, 50, true)
        )
*/


        val layers = arrayOf(
            background,
            backgroundImage!!
        )

        val layerDrawable = LayerDrawable(layers)
        layerDrawable.setLayerInset(0, 1, 1, 1, 1)

        layerDrawable.setLayerInset(1, 10, 10, 10, 10)

        menu[0].icon = layerDrawable



        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            MENU_EDIT -> Toast.makeText(this, "Edit menu item clicked", Toast.LENGTH_SHORT).show()
            MENU_DELETE -> Toast.makeText(
                this,
                "Delete menu item clicked",
                Toast.LENGTH_SHORT
            ).show()
        }
        return false
    }


    /* private fun drawableIconChange(
         view: ImageView,
         color: Int,
         image: Int,
         radius: Float
     ) {

         val background = GradientDrawable()
         background.setColor(ContextCompat.getColor(this@MainActivity, color))

         background.cornerRadius = radius

         background.setStroke(1, color)

         background.shape = GradientDrawable.OVAL

         val backgroundImage = ContextCompat.getDrawable(this@MainActivity, image)

         // change image size

         *//* val bitmap = (backgroundImage as BitmapDrawable).bitmap

         val d: Drawable = BitmapDrawable(
             resources,
             Bitmap.createScaledBitmap(bitmap, 50, 50, true)
         )*//*


        val layers = arrayOf(
            background,
            backgroundImage!!
        )

        val layerDrawable = LayerDrawable(layers)
        layerDrawable.setLayerInset(0, 1, 1, 1, 1)

        layerDrawable.setLayerInset(1, 1, 1, 1, 1)
        view.background = layerDrawable

    }*/
}
