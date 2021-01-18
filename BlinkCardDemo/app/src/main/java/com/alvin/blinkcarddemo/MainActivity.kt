package com.alvin.blinkcarddemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.microblink.blinkcard.MicroblinkSDK
import com.microblink.blinkcard.entities.recognizers.Recognizer
import com.microblink.blinkcard.entities.recognizers.RecognizerBundle
import com.microblink.blinkcard.entities.recognizers.blinkcard.BlinkCardRecognizer
import com.microblink.blinkcard.uisettings.ActivityRunner
import com.microblink.blinkcard.uisettings.BlinkCardUISettings
import com.microblink.blinkcard.uisettings.CameraSettings

class MainActivity : AppCompatActivity() {

    private var mRecognizer: BlinkCardRecognizer? = null
    private var mRecognizerBundle: RecognizerBundle? = null

    companion object {
        const val MY_REQUEST_CODE = 98
        //const val KEY = "GG"

        const val KEY =
                "sRwAAAAXY29tLmFsdmluLmJsaW5rY2FyZGRlbW9AIm6VPzExvpWGxj9GbzZ/zbFLWkyg3JBQRzWipUCNbCdx0Hejy5qZX30fEUdL/YHq2Rn3iCOppiGtWVgDV80uqUwWe3tVd24biKCDh6O95Wi8W1WHWJaYfqvZ+UE4fACaywJmKK8z6yzfYCPdDC4qfqVESOS+kwDFSKRHiByBNyKx3WUtyrYjr+AecFeyBAz7I4Svw9Weel/CB3kweJfg4yOzPlkt5z6fB+SgL2rs"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*var key = ""

        intent?.getStringExtra(KEY)?.let {
            key = it
        }*/

        // findViewById<TextView>(R.id.license_key).text = ("Licence Key : $key")

        MicroblinkSDK.setLicenseKey(
                KEY,
                this
        )


        findViewById<Button>(R.id.button).setOnClickListener {
            startScanning()
        }

        mRecognizer = BlinkCardRecognizer()

        // bundle recognizers into RecognizerBundle

        // bundle recognizers into RecognizerBundle
        mRecognizerBundle = RecognizerBundle(mRecognizer)


    }

    private fun startScanning() {
        // Settings for BlinkCardActivity
        val settings = BlinkCardUISettings(mRecognizerBundle)

       /* // tweak settings as you wish
        settings.apply {
            setShowGlareWarning(true)
            setCameraSettings(CameraSettings.Builder())
        }
*/

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

                    findViewById<TextView>(R.id.card_no).text =
                            ("Card No : ${result.cardNumber}")
                    /*findViewById<TextView>(R.id.card_no_prefix).text =
                            ("Card No Prefix : ${result.cardNumberPrefix}")*/
                    findViewById<TextView>(R.id.card_exp_date).text =
                            ("Card Exp Date : ${result.expiryDate.originalDateString}")
                    findViewById<TextView>(R.id.card_holder_name).text =
                            ("Card Holder Name : ${result.owner}")
                    findViewById<TextView>(R.id.card_type).text =
                            ("Card Type : ${result.issuer}")

                }
            }
        }
    }


}