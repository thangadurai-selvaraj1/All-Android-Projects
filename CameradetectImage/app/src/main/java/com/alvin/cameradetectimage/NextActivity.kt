package com.alvin.cameradetectimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.microblink.MicroblinkSDK

class NextActivity : AppCompatActivity() {
    companion object {
        const val KEY = "sRwAAAAbY29tLmFsdmluLmNhbWVyYWRldGVjdGltYWdlDRzvDpC6qAJ6OkSQdQdtJk43xb7oUilGymAHbur74bLJ87BH+w+zYvhncA0Jddxlm/0eY33hyxMXMQXBg6PhUVfb7SRbGuQrSf4z6y2ucJ/+gaw1BFCqEWpt6e54ssI0tUSPJOWuUYhBuieaVu1WNibRHsIR1WSVTQ6ee4BTaOMILaQXRAjb2BKPTF5+SfSe8+KNW/u5J4HgFY+PcNcEJxq8v0qyr+TsB6RzpuG+2g=="
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)
       // MicroblinkSDK.setLicenseKey(KEY, this)
       /// MicroblinkSDK.setLicenseFile("MB_com.alvin.cameradetectimage_BlinkCard_Android_2020-12-23.mblic", this)
    }
}