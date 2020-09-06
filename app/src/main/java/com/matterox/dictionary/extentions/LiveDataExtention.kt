package com.matterox.dictionary.extentions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> Fragment.observe(liveData: LiveData<T>, observer: Observer<T>) {
    liveData.observe(this.viewLifecycleOwner, observer)
}