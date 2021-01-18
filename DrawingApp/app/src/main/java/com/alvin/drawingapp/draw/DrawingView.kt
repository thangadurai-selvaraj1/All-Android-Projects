package com.alvin.drawingapp.draw

import android.content.Context
import android.graphics.*
import android.view.View

class DrawingView(context: Context) : View(context) {

    private var mPaint = Paint()


    private var mCurX = 0f
    private var mCurY = 0f
    private var mStartX = 0f
    private var mStartY = 0f

    init {
        mPaint.apply {
            color = Color.BLUE
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            strokeWidth = 8f
            isAntiAlias = true
        }
    }

    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    val rect = RectF()
    override fun onDraw(canva: Canvas?) {
        super.onDraw(canva)
        canvas.drawBitmap(bitmap, null, rect, mPaint)

    }
}