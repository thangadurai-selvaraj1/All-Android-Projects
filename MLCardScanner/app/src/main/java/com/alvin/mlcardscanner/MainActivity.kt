package com.alvin.mlcardscanner

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.SparseArray
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.Detector.Detections
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.text.TextBlock
import com.google.android.gms.vision.text.TextRecognizer
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private var mCameraSource: CameraSource? = null
    private var mTextRecognizer: TextRecognizer? = null
    private var mSurfaceView: SurfaceView? = null
    private var mTextView: TextView? = null

    private val RC_HANDLE_CAMERA_PERM = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            val bitmap = getBitmapFromView(
                surfaceView,
                surfaceView.height,
                surfaceView.width
            )
            bitmap?.let {

                val textRecognizer = TextRecognizer.Builder(applicationContext).build()
                /* var s: Bitmap? = null
                 ContextCompat.getDrawable(this, R.drawable.credit_card_1)?.let {
                     s = drawableToBitmap(it)
                 }*/

                val imageFrame: Frame = Frame.Builder()
                    .setBitmap(it) // your image bitmap
                    .build()


                var imageText = ""


                val textBlocks: SparseArray<TextBlock> = textRecognizer.detect(imageFrame)

                for (i in 0 until textBlocks.size()) {
                    val textBlock: TextBlock = textBlocks[textBlocks.keyAt(i)]
                    imageText += textBlock.value // return string
                }

                Toast.makeText(this,imageText,Toast.LENGTH_LONG).show()

            }


        }



        mSurfaceView = findViewById<SurfaceView>(R.id.surfaceView)
        mTextView = findViewById<TextView>(R.id.textView)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            startTextRecognizer()
        } else {
            askCameraPermission()
        }
    }

    private fun getBitmapFromView(view: View, height: Int, width: Int): Bitmap? {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) bgDrawable.draw(canvas) else canvas.drawColor(Color.WHITE)
        view.draw(canvas)
        return bitmap
    }


    override fun onDestroy() {
        super.onDestroy()
        mCameraSource!!.release()
    }

    fun getBitmap(): Bitmap? {
        mSurfaceView?.setDrawingCacheEnabled(true)
        mSurfaceView?.buildDrawingCache(true)
        val bitmap: Bitmap? = mSurfaceView?.getDrawingCache()?.let { Bitmap.createBitmap(it) }
        mSurfaceView?. setDrawingCacheEnabled(false)
        mSurfaceView?.destroyDrawingCache()
        return bitmap
    }

    private fun startTextRecognizer() {
        mTextRecognizer = TextRecognizer.Builder(applicationContext).build()
        if (!mTextRecognizer!!.isOperational) {
            Toast.makeText(applicationContext, "Oops ! Not able to start the text recognizer ...", Toast.LENGTH_LONG).show()
        } else {
            mCameraSource = CameraSource.Builder(applicationContext, mTextRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024)
                    .setRequestedFps(15.0f)
                    .setAutoFocusEnabled(true)
                    .build()
            mSurfaceView!!.holder.addCallback(object : SurfaceHolder.Callback {
                override fun surfaceCreated(holder: SurfaceHolder) {
                    if (ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        try {
                            mCameraSource?.start(mSurfaceView!!.holder)
                            val b = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
                            val c = Canvas(b)
                            mSurfaceView?.draw(c)



                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    } else {
                        askCameraPermission()
                    }
                }

                override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}
                override fun surfaceDestroyed(holder: SurfaceHolder) {
                    mCameraSource?.stop()
                }
            })
            mTextRecognizer?.setProcessor(object : Detector.Processor<TextBlock?> {
                override fun release() {}
                override fun receiveDetections(detections: Detections<TextBlock?>) {
                    val items: SparseArray<TextBlock?> = detections.detectedItems
                    val stringBuilder = StringBuilder()
                    for (i in 0 until items.size()) {
                        val item: TextBlock? = items.valueAt(i)
                        if (item != null && item.value != null) {
                            stringBuilder.append(item.value.toString() + " ")
                        }
                    }
                    val fullText = stringBuilder.toString()
                    val handler = Handler(Looper.getMainLooper())
                    handler.post(Runnable { mTextView!!.text = fullText })
                }
            })
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String?>,
                                            grantResults: IntArray) {
        if (requestCode != RC_HANDLE_CAMERA_PERM) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            return
        }
        if (grantResults.size != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startTextRecognizer()
            return
        }
    }

    private fun askCameraPermission() {
        val permissions = arrayOf<String>(Manifest.permission.CAMERA)
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(this, permissions, RC_HANDLE_CAMERA_PERM)
            return
        }
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
            Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888) // Single color bitmap will be created of 1x1 pixel
        } else {
            Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        }
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }/*
    private fun getCardDetails(bitmap: Bitmap) {
        val image = FirebaseVisionImage.fromBitmap(bitmap)
        val firebaseVisionTextDetector = FirebaseVision.getInstance().cloudTextRecognizer

        firebaseVisionTextDetector.processImage(image)
                .addOnSuccessListener {
                    val words = it.text.split("\n")
                    for (word in words) {
                        Log.e("TAG", word)
                        //REGEX for detecting a credit card
                        if (word.replace(" ", "").matches(Regex("^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})\$")))
                            tvCardNo.text = word
                        //Find a better way to do this
                        if (word.contains("/")) {
                            for (year in word.split(" ")) {
                                if (year.contains("/"))
                                    tvExp.text = year
                            }
                        }
                    }
                    //   sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                    pb.visibility = View.GONE
                }
                .addOnFailureListener {
                    pb.visibility = View.GONE
                    Toast.makeText(baseContext, "Sorry, something went wrong!", Toast.LENGTH_SHORT).show()
                }
    }*/
}