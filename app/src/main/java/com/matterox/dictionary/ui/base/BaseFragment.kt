package com.matterox.dictionary.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.matterox.dictionary.BuildConfig
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.kcontext

@Suppress("LeakingThis")
abstract class BaseFragment(@LayoutRes val layoutId: Int): Fragment(layoutId),
    KodeinAware {

    final override val kodeinContext = kcontext<Fragment>(this)

    final override val kodein by kodein()

    final override val kodeinTrigger: KodeinTrigger? by lazy {
        super.kodeinTrigger
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        kodeinTrigger?.trigger()
    }
}