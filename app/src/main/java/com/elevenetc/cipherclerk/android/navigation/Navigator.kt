package com.elevenetc.cipherclerk.android.navigation

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class Navigator(private val config: Config) {

    private var rootActivity: AppCompatActivity? = null

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

    fun goBack() {
        rootActivity?.supportFragmentManager?.popBackStack()
    }

    fun addRootScreen(fragment: Fragment) {
        rootActivity?.supportFragmentManager!!
            .beginTransaction()
            .add(config.rootContainerId, fragment)
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

    data class Config(
        val app: Application,
        val rootActivity: Class<out AppCompatActivity>,
        val rootContainerId: Int
    )

    class BottomNavAdapter(
        val fragments: List<Fragment>,
        val ids: List<Int>,
        val container: Int
    ) {
        init {

        }
    }
}