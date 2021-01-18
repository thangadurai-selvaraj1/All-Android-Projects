package com.alvin.cameradetectimage

import android.app.Application
import com.microblink.MicroblinkSDK

class MyApp : Application() {
    override fun onCreate() {
         super.onCreate()
       MicroblinkSDK.setLicenseFile("MB_com.alvin.blinkcarddemo_BlinkCard_Android_2020-12-25.mblic", this)
    }


}