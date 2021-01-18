package com.example.permisstions

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.content.DialogInterface
import android.graphics.Color
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AlertDialog
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.EditText
import android.widget.Toast


class NewPermisstions : AppCompatActivity() {

    val permss = arrayOf(
        "camera", "location", "readstorage", "writestorage", "audio", "readcontacts", "writecontacts"
    )
    lateinit var activity: Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_permisstions)
        val btReq: Button = findViewById(R.id.reqNewPermisstion)

        btReq.setOnClickListener(View.OnClickListener {
            var result: Boolean = checkAndRequestPermissions(this, permss)
            Log.i("result", "$result")
        })
    }

    private fun checkAndRequestPermissions(activity: Activity, permissions: Array<String>): Boolean {
        this.activity = activity
        val listPermissionsNeeded = ArrayList<String>()

        for (i in permissions.indices) {

            if (permissions[i].toLowerCase() == "camera") {
                val camerapermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                if (camerapermission != PackageManager.PERMISSION_GRANTED) {
                    listPermissionsNeeded.add(Manifest.permission.CAMERA)
                }
            } else if (permissions[i].toLowerCase() == "readstorage") {
                val writeStoragePermission =
                    ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                if (writeStoragePermission != PackageManager.PERMISSION_GRANTED) {
                    listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            } else if (permissions[i].toLowerCase() == "writestorage") {
                val readStoragePermission =
                    ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)
                if (readStoragePermission != PackageManager.PERMISSION_GRANTED) {
                    listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            } else if (permissions[i].toLowerCase() == "location") {
                val permissionLocation =
                    ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
                    listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION)
                }
            } else if (permissions[i].toLowerCase() == "audio") {
                val permissionRecordAudio =
                    ActivityCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO)
                if (permissionRecordAudio != PackageManager.PERMISSION_GRANTED) {
                    listPermissionsNeeded.add(Manifest.permission.RECORD_AUDIO)
                }
            } else if (permissions[i].toLowerCase() == "readcontacts") {
                val readPermisstionContacts =
                    ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS)
                if (readPermisstionContacts != PackageManager.PERMISSION_GRANTED) {
                    listPermissionsNeeded.add(Manifest.permission.READ_CONTACTS)
                }
            } else if (permissions[i].toLowerCase() == "writecontacts") {
                val writePermisstionContacts =
                    ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_CONTACTS)
                if (writePermisstionContacts != PackageManager.PERMISSION_GRANTED) {
                    listPermissionsNeeded.add(Manifest.permission.WRITE_CONTACTS)
                }
            }

        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(
                activity,
                listPermissionsNeeded.toTypedArray(),
                REQUEST_ID_MULTIPLE_PERMISSIONS
            )
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_ID_MULTIPLE_PERMISSIONS -> {

                val perms = HashMap<String, Int>()
                perms[Manifest.permission.CAMERA] = PackageManager.PERMISSION_GRANTED
                perms[Manifest.permission.WRITE_EXTERNAL_STORAGE] = PackageManager.PERMISSION_GRANTED
                perms[Manifest.permission.READ_EXTERNAL_STORAGE] = PackageManager.PERMISSION_GRANTED
                perms[Manifest.permission.ACCESS_FINE_LOCATION] = PackageManager.PERMISSION_GRANTED
                perms[Manifest.permission.RECORD_AUDIO] = PackageManager.PERMISSION_GRANTED
                perms[Manifest.permission.READ_CONTACTS] = PackageManager.PERMISSION_GRANTED
                perms[Manifest.permission.WRITE_CONTACTS] = PackageManager.PERMISSION_GRANTED

                if (grantResults.size > 0) {
                    for (i in permissions.indices)
                        perms[permissions[i]] = grantResults[i]

                    if (perms[Manifest.permission.CAMERA] == PackageManager.PERMISSION_GRANTED
                        && perms[Manifest.permission.WRITE_EXTERNAL_STORAGE] == PackageManager.PERMISSION_GRANTED
                        && perms[Manifest.permission.READ_EXTERNAL_STORAGE] == PackageManager.PERMISSION_GRANTED
                        && perms[Manifest.permission.ACCESS_FINE_LOCATION] == PackageManager.PERMISSION_GRANTED
                        && perms[Manifest.permission.RECORD_AUDIO] == PackageManager.PERMISSION_GRANTED
                        && perms[Manifest.permission.READ_CONTACTS] == PackageManager.PERMISSION_GRANTED
                        && perms[Manifest.permission.WRITE_CONTACTS] == PackageManager.PERMISSION_GRANTED
                    ) {

                        Toast.makeText(
                            activity, "Permission Granted", Toast.LENGTH_SHORT
                        ).show()


                    } else {
                        /* Log.d(TAG, "Some permissions are not granted ask again ")
                         Toast.makeText(
                             activity, "Some permissions are not granted ask again ", Toast.LENGTH_SHORT
                         ).show()*/

                        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)
                            || ActivityCompat.shouldShowRequestPermissionRationale(
                                activity,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                            )
                            || ActivityCompat.shouldShowRequestPermissionRationale(
                                activity,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                            )
                            || ActivityCompat.shouldShowRequestPermissionRationale(
                                activity,
                                Manifest.permission.ACCESS_FINE_LOCATION
                            )
                            || ActivityCompat.shouldShowRequestPermissionRationale(
                                activity,
                                Manifest.permission.RECORD_AUDIO
                            )

                            || ActivityCompat.shouldShowRequestPermissionRationale(
                                activity,
                                Manifest.permission.READ_CONTACTS


                            )
                            || ActivityCompat.shouldShowRequestPermissionRationale(
                                activity,
                                Manifest.permission.WRITE_CONTACTS


                            )

                        ) {

                            val builder = android.app.AlertDialog.Builder(activity)
                            builder.setMessage("some Permissions are required for this app")
                            builder.setPositiveButton("Ok") { dialog, which ->

                                checkAndRequestPermissions(activity, permss)
                            }

                            builder.setNegativeButton("No") { dialog, which ->
                                dialog.dismiss()
                            }
                            val dialog: android.app.AlertDialog = builder.create()
                            dialog.show()


                            /*
                               var d=showDialogOK("Service Permissions are required for activity app",
                                   DialogInterface.OnClickListener { dialog, which ->
                                       when (which) {
                                           DialogInterface.BUTTON_POSITIVE ->
                                           DialogInterface.BUTTON_NEGATIVE ->

                                       }
                                   })*/
                        } else {
                            val builder = android.app.AlertDialog.Builder(activity)
                            builder.setMessage("You need to give some mandatory permissions to continue. Do you want to go to app settings?")
                            builder.setPositiveButton("Ok") { dialog, which ->

                                startActivity(
                                    Intent(
                                        android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                        Uri.parse("package:com.example.permisstions")
                                    )
                                )
                            }

                            builder.setNegativeButton("No") { dialog, which ->
                                dialog.dismiss()
                            }
                            val dialog: android.app.AlertDialog = builder.create()
                            dialog.show()


                            // explain("You need to give some mandatory permissions to continue. Do you want to go to app settings?")
                        }
                    }
                }
            }
        }

    }
/*
    private fun showDialogOK(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(activity)
            .setMessage(message)
            .setPositiveButton("OK", okListener)
            .setNegativeButton("Cancel", okListener)
            .create()
            .show()
    }

    private fun explain(msg: String) {
        val dialog = android.support.v7.app.AlertDialog.Builder(activity)
        dialog.setMessage(msg)
            .setPositiveButton("Yes") { paramDialogInterface, paramInt ->
                startActivity(
                    Intent(
                        android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:com.example.permisstions")
                    )
                )
            }
            .setNegativeButton("Cancel") { paramDialogInterface, paramInt -> }
        dialog.show()
    }*/

    companion object {
        val REQUEST_ID_MULTIPLE_PERMISSIONS = 1
    }


}

