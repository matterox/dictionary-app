package com.matterox.dictionary.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelProvider private constructor() {
    companion object {
        inline fun <reified T : ViewModel> of(fragment: Fragment, crossinline factory: () -> T): T {
            @Suppress("UNCHECKED_CAST")
            val vmFactory = object : ViewModelProvider.Factory {
                override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
            }

            return ViewModelProvider(fragment, vmFactory)[T::class.java]
        }
    }
}