package com.matterox.dictionary

import android.app.Application
import android.content.Context
import com.matterox.dictionary.di.appModule
import com.matterox.dictionary.di.dataModule
import com.matterox.dictionary.di.presentationModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import timber.log.Timber

class DictionaryApp: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@DictionaryApp))
        import(appModule)
        import(presentationModule)
        import(dataModule)
    }

    private lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        context = this

        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}