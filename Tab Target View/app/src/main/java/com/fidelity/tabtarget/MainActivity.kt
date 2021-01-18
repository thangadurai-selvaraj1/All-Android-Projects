package com.fidelity.tabtarget


import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import me.toptas.fancyshowcase.FancyShowCaseQueue
import me.toptas.fancyshowcase.FancyShowCaseView
import me.toptas.fancyshowcase.FocusShape
import me.toptas.fancyshowcase.listener.DismissListener
import me.toptas.fancyshowcase.listener.OnViewInflateListener


class MainActivity : AppCompatActivity() {

    private lateinit var queue: FancyShowCaseQueue


    private var dismissListener = object : DismissListener {
        override fun onDismiss(id: String?) {
            Log.v("asd", "dismiss")
        }

        override fun onSkipped(id: String?) {
            Log.v("asd", "skipped")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val one = findViewById<Button>(R.id.one)

        val two = findViewById<Button>(R.id.two)

        val three = findViewById<Button>(R.id.three)

        val fancyShowCaseView1 = FancyShowCaseView.Builder(this)
            .title(
                "25\n" +
                        "\n" +
                        "So the answer is well written in the error. You can't have two projects of same package name. Even if you delete it. It will take alteast 4-5 days to get deleted fully from developer's console.\n" +
                        "\n" +
                        "So only solution is to generate a new SHA-1 key by custom signing the app by generating a signed apk from android studio. Or just create a new project with different package name both"
            )
            .focusOn(one)
            .focusShape(FocusShape.ROUNDED_RECTANGLE)
            .customView(R.layout.layout_my_custom_view, object : OnViewInflateListener {
                override fun onViewInflated(view: View) {
                    view.findViewById<View>(R.id.btn_action_1).setOnClickListener {
                        queue.current?.hide()
                    }
                }
            })
            .closeOnTouch(true)
            .build()


        queue = FancyShowCaseQueue()
            .add(fancyShowCaseView1)

        queue.show()


/*

        MaterialTapTargetPrompt.Builder(this@MainActivity)
            .setTarget(R.id.seven)
            .setPromptFocal(RectanglePromptFocal())
            .setBackButtonDismissEnabled(true)
            .setBackgroundColour(ContextCompat.getColor(this, R.color.colorPrimary))
            .setPromptBackground(RectanglePromptBackground())
            .setPrimaryText("Send your first email")
            .setSecondaryText("Tap the envelope")
            .setPromptStateChangeListener { _, state ->
                if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED) { // User has pressed the prompt target
                    Toast.makeText(this@MainActivity, "ff", Toast.LENGTH_SHORT).show()
                }
            }
            .show()
*/

        val ivBack = findViewById<ImageView>(R.id.ic_back)

        val ivTick = findViewById<ImageView>(R.id.ic_tick)


        /*    FancyShowCaseView.Builder(this)
                .focusShape(FocusShape.ROUNDED_RECTANGLE)
                .focusOn(one)
                .titleStyle(0, Gravity.BOTTOM or Gravity.CENTER)
                .title("Focus on guugguugguugugguug")
                .build()
                .show()
    */
        FancyShowCaseView.Builder(this)
            .title("First Queue Item")
            .focusShape(FocusShape.ROUNDED_RECTANGLE)
            .focusOn(one)

            .customView(R.layout.layout_my_custom_view, object : OnViewInflateListener {
                override fun onViewInflated(view: View) {
                    view.findViewById<View>(R.id.btn_action_1).setOnClickListener {
                        FancyShowCaseView.hideCurrent(this@MainActivity)
                    }
                }
            })
            .closeOnTouch(true)
            //.dismissListener(dismissListener)
            .build()


        /* BubbleShowCaseBuilder(this) //Activity instance
             .title("foo") //Any title for the bubble view
             .targetView(two) //View to point out
             .show()
 */
/*
        GuideView.Builder(this)
            .setTitle("Guide Title Text")
            .setContentText(
                "Guide Description Text\n .....Guide Description Text\n .....Guide Description Text .....You can improve/fix some part of it .\n" +
                        "\n" +
                        "Add Tests:\n" +
                        "\n" +
                        "Assuming that the code in question already has automated (unit) tests, do add tests for the code you submit. This isn't a hard rule. There are various cases where you may need to add code without test coverage (e.g. when adding a Object), but if it can be tested, it should be tested."
            )

            .setContentTypeFace(Typeface.DEFAULT)//optional
            .setTitleTypeFace(Typeface.DEFAULT)//optional
            .setTargetView(one)
            .setDismissType(DismissType.outside)
            .build()
            .show()*/

        one.setOnClickListener {
            drawableIconChange(ivBack, R.color.red, R.drawable.arrow, 20f)
        }
        two.setOnClickListener {
            drawableIconChange(ivBack, R.color.white, R.drawable.arrow, 20f)
        }
        three.setOnClickListener {
            drawableIconChange(ivBack, R.color.grey, R.drawable.arrow, 20f)
        }


        drawableIconChange(ivTick, R.color.red, R.drawable.ic_tick, 10f)

        ivBack.setOnClickListener {
            Toast.makeText(this@MainActivity, "Back Icon Clicked!", Toast.LENGTH_SHORT).show()
        }

        ivTick.setOnClickListener {
            Toast.makeText(this@MainActivity, "Tick Icon Clicked!", Toast.LENGTH_SHORT).show()
        }


    }


    private fun drawableIconChange(
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

        /* val bitmap = (backgroundImage as BitmapDrawable).bitmap

         val d: Drawable = BitmapDrawable(
             resources,
             Bitmap.createScaledBitmap(bitmap, 50, 50, true)
         )*/


        val layers = arrayOf(
            background,
            backgroundImage!!
        )

        val layerDrawable = LayerDrawable(layers)
        layerDrawable.setLayerInset(0, 1, 1, 1, 1)

        layerDrawable.setLayerInset(1, 1, 1, 1, 1)
        view.background = layerDrawable

    }
}
