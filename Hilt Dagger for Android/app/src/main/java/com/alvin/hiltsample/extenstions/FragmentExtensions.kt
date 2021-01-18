package com.alvin.hiltsample.extenstions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

inline fun FragmentManager.transaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

inline fun FragmentManager.showDialog(func: FragmentTransaction.() -> FragmentTransaction): Fragment {
    val fragmentTransaction = beginTransaction()
    val dialog = findFragmentByTag("dialog")
    if (dialog != null) {
        fragmentTransaction.remove(dialog)
    }
    return dialog!!
}