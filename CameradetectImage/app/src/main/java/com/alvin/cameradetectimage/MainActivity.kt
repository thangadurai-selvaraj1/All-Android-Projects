package com.alvin.cameradetectimage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.microblink.entities.recognizers.Recognizer
import com.microblink.entities.recognizers.RecognizerBundle
import com.microblink.entities.recognizers.blinkcard.BlinkCardRecognizer
import com.microblink.uisettings.ActivityRunner
import com.microblink.uisettings.BlinkCardUISettings


class MainActivity : AppCompatActivity() {

    private var mRecognizer: BlinkCardRecognizer? = null
    private var mRecognizerBundle: RecognizerBundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRecognizer = BlinkCardRecognizer()

        // bundle recognizers into RecognizerBundle

        // bundle recognizers into RecognizerBundle
        mRecognizerBundle = RecognizerBundle(mRecognizer)

        startScanning()
    }

    private fun startScanning() {
        // Settings for BlinkCardActivity
        val settings = BlinkCardUISettings(mRecognizerBundle)

        // tweak settings as you wish

        // Start activity
        ActivityRunner.startActivityForResult(this, MY_REQUEST_CODE, settings)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MY_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                // load the data into all recognizers bundled within your RecognizerBundle
                mRecognizerBundle!!.loadFromIntent(data)

                // now every recognizer object that was bundled within RecognizerBundle
                // has been updated with results obtained during scanning session

                // you can get the result by invoking getResult on recognizer
                val result = mRecognizer!!.result
                if (result.resultState == Recognizer.Result.State.Valid) {
                    // result is valid, you can use it however you wish
                }
            }
        }
    }

    companion object {
        const val MY_REQUEST_CODE = 98
    }
}