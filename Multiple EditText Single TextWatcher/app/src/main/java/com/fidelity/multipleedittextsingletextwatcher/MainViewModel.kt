package com.fidelity.multipleedittextsingletextwatcher

import android.text.Editable
import androidx.databinding.ObservableField


class MainViewModel {

    var total = ObservableField<String>("fgh")
    var first = ObservableField<String>("")


    fun afterTextChanged(editable: Editable) {
        total.set("no")

        if (editable.toString() != "") {
            if ((R.id.first).hashCode() == editable.hashCode()) {
                total.set("one")
            }
            if (first.get().hashCode() == editable.hashCode()) {
                total.set("two")
            }
            if ((R.id.third).hashCode() == editable.hashCode()) {
                total.set("three")
            }
        }
    }

    fun onTextChanged(
        s: CharSequence,
        start: Int,
        before: Int,
        count: Int
    ) {
        if (first.get().hashCode() == s.hashCode())
            total.set("one")
    }
}