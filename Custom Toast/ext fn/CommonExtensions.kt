package com.fidelity.fidelityumsuka.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Patterns
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.fidelity.fidelityumsuka.R
import com.fidelity.fidelityumsuka.utils.Constants
import com.fidelity.fidelityumsuka.widget.CustomToast
import java.text.DecimalFormat
import java.util.regex.Pattern

fun String.isValidEmail(): Boolean =
    this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun isValidEmail(email: CharSequence?): Boolean =
    email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches()

fun isNullOrEmpty(value: String?): Boolean =
    value == null || value.isEmpty() || value.equals("null", ignoreCase = true)

fun showToastMessage(context: Context, message: String, duration: Int = 2000) {
    //Toast.makeText(context, message, duration).show()
    
    CustomToast.makeText(context,message,duration,CustomToast.ERROR)
    
}

fun showErrorMessage(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) =
    CustomToast.makeText(context, message, duration, CustomToast.ERROR).show()


fun showWarningMessage(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) =
    CustomToast.makeText(context, message, duration, CustomToast.WARNING).show()



fun isName(value: Int): Boolean = value in 128 downTo 3

fun isMerchantId(value: Int): Boolean = value in 24 downTo 4

fun isValidAccountNo(value: Int): Boolean = value in 20 downTo 12

fun isUsernameValid(value: String): Boolean = Pattern.matches("^[a-z,0-9]", value)

fun isUsername(value: Int): Boolean = value in 64 downTo 6

fun isBusinessName(value: Int): Boolean = value in 56 downTo 5

fun isBrandName(value: Int): Boolean = value in 128 downTo 3

fun isPassword(value: Int): Boolean = value in 24 downTo 6

fun isFirstName(value: Int): Boolean = value in 128 downTo 3

fun isPhoneNumber(value: Int): Boolean = value in 16 downTo 6

fun isOfferName(value: Int): Boolean = value in 5 downTo 256

fun isOfferDesc(value: Int): Boolean = value in 3 downTo 512

fun isOfferCode(value: Int): Boolean = value in 4 downTo 10

fun isAmountValid(amount: String) = amount.matches(Constants.PRICE_PATTERN.toRegex())

fun roundAmount(amount: String?): String {
    if (isNullOrEmpty(amount)) return "0.0"
    var currencyFormat = ""
    try {
        val dblAmount = java.lang.Double.parseDouble(amount!!)
        val decimalFormat = DecimalFormat(".##")
        //currencyFormat = decimalFormat.format(dblAmount)
        currencyFormat = currencyFormat(decimalFormat.format(dblAmount))
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return currencyFormat
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
