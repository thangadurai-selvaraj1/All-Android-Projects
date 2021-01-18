package com.example.permisstions

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build.VERSION_CODES.M
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import java.util.*
import kotlin.collections.ArrayList


class PermissionActivity : AppCompatActivity() {

    val PERMISSION_CODE: Int = 1
    var result: Boolean = false
    val perms = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    lateinit var activity: Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permisstion)

        val btReq: Button = findViewById(R.id.reqPermisstion)

        btReq.setOnClickListener(View.OnClickListener {
            val s = checkPermissions(this, perms)
            //checkPermissionsSafety(perms,11)
            //  Log.i("lll", "$s");
        })
    }


    @TargetApi(M)
    private fun checkPermissionsSafety(permissions: Array<String>, requestCode: Int) {
        for (i in permissions.indices) {
            if (ContextCompat.checkSelfPermission(activity, permissions[i])
                == PackageManager.PERMISSION_GRANTED
            ) {
            } else {
                requestPermissions(permissions, requestCode)
            }
        }
    }

    private fun checkPermissions(activity: Activity, permissions: Array<String>): Boolean {
        this.activity = activity
        var listPermissionsNeeded = ArrayList<String>()
        for (i in permissions) {
            if (ContextCompat.checkSelfPermission(activity, i)
                == PackageManager.PERMISSION_GRANTED
            ) {

                Toast.makeText(activity, "Granted", Toast.LENGTH_SHORT).show()
                result = true
                return result

            } else {
                listPermissionsNeeded.add(i)
                requestPermission(activity, permissions)
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            var array = arrayOfNulls<String>(listPermissionsNeeded.size)
            listPermissionsNeeded.toArray(array)
            requestPermission(activity, listPermissionsNeeded.toArray(array))
        }
        return result
    }

    private fun requestPermission(activity: Activity, permissions: Array<String>) {

        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions[0])) {

            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Permission Needed")
            builder.setMessage("pls enable")
            builder.setPositiveButton("Ok") { dialog, which ->
                ActivityCompat.requestPermissions(activity, permissions, PERMISSION_CODE)
            }

            builder.setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        } else if (!ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                permissions[0]
            )
        ) {

            ActivityCompat.requestPermissions(activity, permissions, PERMISSION_CODE)
        }

    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_CODE) {

            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(activity, "Permission GRANTED", Toast.LENGTH_SHORT).show();
                result = true


            } else if (!ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    permissions[0]
                )
            ) {

                val builder = AlertDialog.Builder(activity)
                builder.setTitle("Permission Needed")
                builder.setMessage("pls go settings")
                builder.setPositiveButton("Ok") { dialog, which ->

                    val intent = Intent()
                    intent.action = android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                }

                builder.setNegativeButton("No") { dialog, which ->
                    dialog.dismiss()
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()


            } else {
                Toast.makeText(activity, "Permission de", Toast.LENGTH_SHORT).show();
            }


        }
    }

    /*  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
          super.onRequestPermissionsResult(requestCode, permissions, grantResults)

          if (requestCode==11){
              if (grantResults.size > 0){

                  for(i in permissions.indices){
                      print("Index $i")

                  }

              }
          }



      }*/
}
