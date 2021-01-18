package com.alvin.cameradetectimage;

import android.app.Application;

import com.microblink.MicroblinkSDK;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MicroblinkSDK.setLicenseKey("sRwAAAAbY29tLmFsdmluLmNhbWVyYWRldGVjdGltYWdlDRzvDpC6qAJ6OkSQcYFuJm+BWa/IKX+EKpNPHhBiywOtYiUqZTfOkzzavvkarimwLv8eUoazMiTq4jR8Lh3kNVKIi1m4VH0sSeDAijhA9YwSPZRXrmNwOsSlV5LkRlZIMEU18wgKVOCpsb+R2CniiSDXslZpevvo8DW+/HDhSU27ZXdsJKeDxt2X+a92eYsOShiyt/3RoTXo+Gk8lo2n7waCPv6Rb2DTUNBbwnaXZw==", this);
    }
}