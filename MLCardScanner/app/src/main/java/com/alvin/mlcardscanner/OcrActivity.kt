package com.alvin.mlcardscanner

import android.R.attr.bitmap
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.SparseArray
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.text.TextBlock
import com.google.android.gms.vision.text.TextRecognizer
import kotlinx.android.synthetic.main.activity_ocr.*


class OcrActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ocr)

        val textRecognizer = TextRecognizer.Builder(applicationContext).build()
        var s: Bitmap? = null
        ContextCompat.getDrawable(this, R.drawable.credit_card_1)?.let {
            s = drawableToBitmap(it)
        }

        val imageFrame: Frame = Frame.Builder()
            .setBitmap(s) // your image bitmap
            .build()


        var imageText = ""


        val textBlocks: SparseArray<TextBlock> = textRecognizer.detect(imageFrame)

        for (i in 0 until textBlocks.size()) {
            val textBlock: TextBlock = textBlocks[textBlocks.keyAt(i)]
            imageText += textBlock.value // return string
        }

        v.setText(imageText)
    }

    fun drawableToBitmap(drawable: Drawable): Bitmap? {
        var bitmap: Bitmap? = null
        if (drawable is BitmapDrawable) {
            val bitmapDrawable = drawable
            if (bitmapDrawable.bitmap != null) {
                return bitmapDrawable.bitmap
            }
        }
        bitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
            Bitmap.createBitmap(
                1,
                1,
                Bitmap.Config.ARGB_8888
            ) // Single color bitmap will be created of 1x1 pixel
        } else {
            Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
        }
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}