package com.matterox.dictionary

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.matterox.dictionary.ui.base.BaseActivity
import com.matterox.dictionary.ui.base.navigation.NavManager
import kotlinx.android.synthetic.main.main_activity.*
import org.kodein.di.generic.instance

class MainActivity : BaseActivity(R.layout.main_activity) {

    private val navController get() = nav_host_fragment.findNavController()
    private val navManager by instance<NavManager>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavManager()
    }

    private fun initNavManager() {
        navManager.setOnNavEvent {
            navController.navigate(it)
        }
    }
}