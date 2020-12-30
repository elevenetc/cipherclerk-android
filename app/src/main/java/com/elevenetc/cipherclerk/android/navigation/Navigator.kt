package com.elevenetc.cipherclerk.android.navigation

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.elevenetc.cipherclerk.android.R

class Navigator(val config: Config) {

    var rootActivity: AppCompatActivity? = null

    init {
        config.app.registerActivityLifecycleCallbacks(object : ActivityLifeCycleCallback() {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                if (config.rootActivity.isInstance(activity)) {
                    rootActivity = activity as AppCompatActivity
                }
            }

            override fun onActivityDestroyed(activity: Activity) {
                if (config.rootActivity.isInstance(activity)) {
                    rootActivity = null
                }
            }
        })
    }

    fun addRootScreen(fragment: Fragment) {
        rootActivity?.supportFragmentManager!!
            .beginTransaction()
            .add(R.id.root, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun addSubScreen(sub: Fragment, child: Fragment, containerId: Int) {
        sub.childFragmentManager
            .beginTransaction()
            .replace(containerId, child)
            .commit()
    }

    private fun getRootFragmentManager(fragment: Fragment): FragmentManager {
        val parentFragment = fragment.parentFragment
        return if (parentFragment == null) {
            fragment.parentFragmentManager
        } else {
            getRootFragmentManager(parentFragment)
        }
    }

    data class Config(val app: Application, val rootActivity: Class<out AppCompatActivity>)
}