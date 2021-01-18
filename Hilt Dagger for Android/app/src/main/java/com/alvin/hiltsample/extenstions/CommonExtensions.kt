package com.alvin.hiltsample.extenstions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Patterns
import android.widget.Toast
import androidx.core.content.ContextCompat
import java.text.DecimalFormat
import kotlin.math.floor


fun showToastMessage(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(context, message, duration).show()

/*
fun showErrorMessage(context: Context, message: String, duration: Int = MDToast.LENGTH_SHORT) =
    MDToast.makeText(context, message, duration, MDToast.TYPE_ERROR).show()


fun showSuccessMessage(context: Context, message: String, duration: Int = MDToast.LENGTH_SHORT) =
    MDToast.makeText(context, message, duration, MDToast.TYPE_SUCCESS).show()

fun showErrorMessage(context: Context, message: Int, duration: Int = MDToast.LENGTH_SHORT) =
    MDToast.makeText(context, context.getString(message), duration, MDToast.TYPE_ERROR).show()
*/

fun roundAmount(amount: String?): String {
    if (isNullOrEmpty(amount) || amount.equals("0", ignoreCase = true)) return "0.0"
    var currencyFormat = ""
    try {
        val dblAmount = java.lang.Double.parseDouble(amount!!)
        val decimalFormat = DecimalFormat(".##")
        currencyFormat = currencyFormat(decimalFormat.format(dblAmount))
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return currencyFormat
}

fun roundDown(amount: Double?): Double {
    if (amount == null) return 0.0
    if (amount < 0 || amount < 0.0) return 0.0
    return floor(amount)
}

fun currencyFormat(amount: String): String {
    val formatter = DecimalFormat("##,##,##0.00")
    return formatter.format(java.lang.Double.parseDouble(amount))
}

fun Int.toBitmap(context: Context): Bitmap? {
    // retrieve the actual drawable
    val drawable = ContextCompat.getDrawable(context, this) ?: return null
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    val bm = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    // draw it onto the bitmap
    val canvas = Canvas(bm)
    drawable.draw(canvas)
    return bm
}


fun emailReplaceWithStar(email: String): String {
    val splitValue = email.split("@")

    var starValue = ""

    if (splitValue[0].length > 2) {
        val charArrayValues = splitValue[0].toCharArray()

        for (i in charArrayValues.indices) {
            when (i) {
                0 -> {
                    starValue += charArrayValues[0]
                }
                charArrayValues.size - 1 -> {
                    starValue += charArrayValues[charArrayValues.size - 1]
                }
                else -> {
                    starValue += "*"
                }
            }
        }

        return "$starValue@${splitValue[1]}"
    } else {
        return email
    }


}

fun mobileNumberReplaceWithStar(email: String): String {
    val splitValue = email.split("@")

    var starValue = ""

    val charArrayValues = splitValue[0].toCharArray()

    for (i in charArrayValues.indices) {
        if (i == 0) {
            starValue += charArrayValues[0]
        } else {
            starValue += "*"
        }
    }
    return "$starValue${splitValue[1]}"
}

fun String.capitalizeWords(): String =
    split(" ").joinToString(" ") { it.toLowerCase().capitalize() }
