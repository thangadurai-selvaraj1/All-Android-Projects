package com.alvin.hiltsample.extenstions

import android.util.Patterns
import java.util.regex.Pattern

fun isUsernameValid(value: String): Boolean = Pattern.matches("^[a-z,0-9]", value)

fun isUsername(value: Int): Boolean = value in 64 downTo 6

fun isName(value: Int): Boolean = value in 128 downTo 3

fun isValidName(value: String): String {
    if (value.endsWith("  ")) {
        return value.replace("\\s{2,}", " ")
    }
    return value
}

fun isPassword(value: Int): Boolean = value in 24 downTo 6

fun isPhoneNumber(value: Int): Boolean = value == 7

fun String.isValidEmail(): Boolean =
    this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun isValidEmail(email: CharSequence?): Boolean =
    email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches()

fun isNullOrEmpty(value: String?): Boolean =
    value == null || value.isEmpty() || value.equals(null, ignoreCase = true)

/*

fun isAmountValid(amount: String?) = amount?.matches(Constants.PRICE_PATTERN.toRegex())
*/

