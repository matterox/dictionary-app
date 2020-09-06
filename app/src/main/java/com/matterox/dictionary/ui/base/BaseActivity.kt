package com.matterox.dictionary.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.matterox.dictionary.BuildConfig
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.kodein
import org.kodein.di.android.retainedKodein

abstract class BaseActivity(@LayoutRes val layoutResId: Int):
    AppCompatActivity(layoutResId), KodeinAware {

    private val parentKodein by kodein()

    final override val kodein: Kodein by retainedKodein {
        extend(parentKodein)
    }

    final override val kodeinTrigger: KodeinTrigger? by lazy {
        super.kodeinTrigger
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        kodeinTrigger?.trigger()
        supportActionBar?.hide()
    }
}