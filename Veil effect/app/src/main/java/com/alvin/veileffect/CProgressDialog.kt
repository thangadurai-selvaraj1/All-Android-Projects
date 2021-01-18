package com.alvin.veileffect

import android.app.Activity
import android.app.Dialog

class CProgressDialog(private val activity: Activity) {
    private var dialog: Dialog? = null

    init {
        showProgressDialog()
    }

    /**
     * create progress dialog
     */
    private fun showProgressDialog() {
        dialog = Dialog(activity)
        dialog?.setCancelable(false)
        dialog?.setContentView(R.layout.layout_veil)
        dialog?.window?.setBackgroundDrawable(null)
    }

    /**
     * check progress dialog is shown
     */
    fun isShowing(): Boolean {
        return dialog?.isShowing!!
    }

    /**
     * show progress dialog
     */
    fun show() {
        dialog?.show()
    }

    /**
     * dismiss progress dialog
     */
    fun dismiss() {
        dialog?.dismiss()
    }
}
