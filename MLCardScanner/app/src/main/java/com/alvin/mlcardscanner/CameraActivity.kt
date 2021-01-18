package com.alvin.mlcardscanner

import android.graphics.BitmapFactory
import android.hardware.Camera
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.util.SparseArray
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.text.TextBlock
import com.google.android.gms.vision.text.TextRecognizer
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class CameraActivity : AppCompatActivity() {

    private var mCamera: Camera? = null
    private var mCameraPreview: CameraPreview? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        mCamera = getCameraInstance()
        mCameraPreview = CameraPreview(this, mCamera)
        val preview = findViewById<FrameLayout>(R.id.camera_preview)
        preview.addView(mCameraPreview)

        button_capture.setOnClickListener {
            mCamera?.takePicture(null, null, mPicture)


        }

    }

    var mPicture: Camera.PictureCallback = object : Camera.PictureCallback {
        override fun onPictureTaken(data: ByteArray?, camera: Camera?) {
            val pictureFile: File = getOutputMediaFile() ?: return
            try {
                val fos = FileOutputStream(pictureFile)
                fos.write(data)

                val filePath: String = pictureFile.getPath()
                val bitmap = BitmapFactory.decodeFile(filePath)

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

                    Toast.makeText(this@CameraActivity,imageText, Toast.LENGTH_LONG).show()

                }

                fos.close()
            } catch (e: FileNotFoundException) {
            } catch (e: IOException) {
            }
        }
    }

    private fun getCameraInstance(): Camera? {
        var camera: Camera? = null
        try {
            camera = Camera.open()
        } catch (e: Exception) {
            // cannot get camera or does not exist
        }
        return camera
    }
    private fun getOutputMediaFile(): File? {
        val mediaStorageDir = File(
            Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
            "MyCameraApp"
        )
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory")
                return null
            }
        }
        // Create a media file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss")
            .format(Date())
        val mediaFile: File
        mediaFile = File(
            mediaStorageDir.path + File.separator
                    + "IMG_" + timeStamp + ".jpg"
        )
        return mediaFile
    }

}