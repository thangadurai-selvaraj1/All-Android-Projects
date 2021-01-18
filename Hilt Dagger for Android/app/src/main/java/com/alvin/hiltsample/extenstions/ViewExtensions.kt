package com.alvin.hiltsample.extenstions

import android.app.Activity
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.SeekBar
import androidx.annotation.LayoutRes
import androidx.viewpager.widget.ViewPager
import com.google.android.material.textview.MaterialTextView
import java.util.*


fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun MaterialTextView.setDrawableColor(color: Int) {
    compoundDrawables.filterNotNull().forEach {
        it.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
    }
}


fun CheckBox.onChecked(action: (compoundButton: CompoundButton, isChecked: Boolean) -> Unit) {
    setOnCheckedChangeListener { compoundButton, b ->
        action(compoundButton, b)
    }
}

fun EditText.onTextChanged(action: (CharSequence) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(string: Editable?) = Unit
        override fun beforeTextChanged(string: CharSequence?, start: Int, count: Int, after: Int) =
            Unit

        override fun onTextChanged(string: CharSequence?, start: Int, before: Int, count: Int) {
            action(string ?: "")
        }
    })
}

fun EditText.afterTextChanged(activity: Activity, action: (Editable) -> Unit) {
    var timer: Timer? = null
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable) {
            timer = Timer()
            timer?.schedule(object : TimerTask() {
                override fun run() {
                    activity.runOnUiThread { action(editable) }
                }
            }, 500)
        }

        override fun beforeTextChanged(string: CharSequence?, start: Int, count: Int, after: Int) =
            Unit

        override fun onTextChanged(string: CharSequence?, start: Int, before: Int, count: Int) {
            if (timer != null) {
                timer?.cancel()
            }
        }
    })
}

fun EditText.onMaxLength(length: Int) {
    val filterArray = arrayOfNulls<InputFilter>(1)
    filterArray[0] = InputFilter.LengthFilter(length)
    filters = filterArray
}

fun EditText.clearOnTextChangedListener() {
    onTextChanged {}
}

fun ViewPager.pageChangeListener(action: (Int) -> Unit) {
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) = Unit
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) = Unit

        override fun onPageSelected(position: Int) {
            action(position)
        }
    })
}

fun SeekBar.seekBarChangeListener(action: (SeekBar, Int, Boolean) -> Unit) {
    setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            action(seekBar, if (progress < 1) progress else 1, fromUser)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit

        override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit
    })
}

fun View.showView() {
    visibility = VISIBLE
}

fun View.hideView() {
    visibility = INVISIBLE
}

fun View.removeView() {
    visibility = GONE
}
