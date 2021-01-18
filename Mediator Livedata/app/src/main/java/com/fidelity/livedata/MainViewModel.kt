package com.fidelity.livedata

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel : ViewModel() {


    val live1 = MutableLiveData<Int>(0)

    val live2 = MutableLiveData<Int>(1)

    val mid = MediatorLiveData<Int>()

    var name = ObservableField<String>("df")

    init {
        /*mid.addSource(live1) {
            mid.value = it
        }
        mid.addSource(live2) {
            mid.value = it
        }*/
        zipLiveData(live1,live2)
    }


    fun <A, B> zipLiveData(a: LiveData<A>, b: LiveData<B>): LiveData<Pair<A, B>> {
        return MediatorLiveData<Pair<A, B>>().apply {
            var lastA: A? = null
            var lastB: B? = null

            fun update() {
                val localLastA = lastA
                val localLastB = lastB
                if (localLastA != null && localLastB != null)
                    this.value = Pair(localLastA, localLastB)
            }

            addSource(a) {
                lastA = it

                update()
            }
            addSource(b) {
                lastB = it

                update()
            }
        }
    }

}