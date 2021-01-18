package com.fidelity.fidelityumsuka.widget


import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.fidelity.fidelityumsuka.R


class CustomToast(context: Context?) : Toast(context) {
    companion object {
        /**
         * Construct an empty Toast object.  You must call [.setView] before you
         * can call [.show].
         *
         * @param context The context to use.  Usually your [Application]
         * or [Activity] object.
         */
        var SUCCESS = 1
        var WARNING = 2
        var ERROR = 3
        var INFO = 4
        var DEFAULT = 5
        var CONFUSING = 6
        fun makeText(
            context: Context?,
            message: String?,
            duration: Int,
            type: Int
        ): Toast {
            val toast = Toast(context)
            toast.duration = duration
            val layout: View =
                LayoutInflater.from(context).inflate(R.layout.custom_toast_layout, null, false)
            val l1 = layout.findViewById(R.id.toast_text) as TextView
            val linearLayout = layout.findViewById(R.id.toast_type) as LinearLayout
            val img: ImageView = layout.findViewById(R.id.toast_icon) as ImageView
            l1.text = message

            when (type) {
                1 -> {
                    linearLayout.setBackgroundResource(R.drawable.success_toast_shape)
                    img.setImageResource(R.drawable.ic_happy_emoji)
                }
                2 -> {
                    linearLayout.setBackgroundResource(R.drawable.warning_toast_shape)
                    img.setImageResource(R.drawable.ic_warning_emoji)
                }
                3 -> {
                    linearLayout.setBackgroundResource(R.drawable.failure_toast_shape)
                    img.setImageResource(R.drawable.ic_sad_emoji)
                }
                4 -> {
                    linearLayout.setBackgroundResource(R.drawable.info_toast_shape)
                    img.setImageResource(R.drawable.ic_info_emoji)
                }
            }
            toast.view = layout
            return toast
        }

   /*     fun makeText(
            context: Context?,
            message: String?,
            duration: Int,
            type: Int,
            ImageResource: Int,
            androidIcon: Boolean
        ): Toast {
            val toast = Toast(context)
            val layout: View =
                LayoutInflater.from(context).inflate(R.layout.custom_toast_layout, null, false)
            val l1 = layout.findViewById(R.id.toast_text) as TextView
            val linearLayout = layout.findViewById(R.id.toast_type) as LinearLayout
            val img: ImageView = layout.findViewById(R.id.toast_icon) as ImageView
            l1.text = message
            img.setImageResource(ImageResource)

            when (type) {
                1 -> linearLayout.setBackgroundResource(R.drawable.success_toast_shape)
                2 -> linearLayout.setBackgroundResource(R.drawable.warning_toast_shape)
                3 -> linearLayout.setBackgroundResource(R.drawable.failure_toast_shape)
                4 -> linearLayout.setBackgroundResource(R.drawable.info_toast_shape)
            }
            toast.view = layout
            return toast
        }*/
    }
}