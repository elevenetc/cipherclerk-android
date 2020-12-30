package com.elevenetc.cipherclerk.android.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.elevenetc.cipherclerk.android.R
import com.elevenetc.cipherclerk.android.navigation.Navigator
import com.elevenetc.cipherclerk.android.ui.dashboard.DashboardFragment
import com.elevenetc.cipherclerk.android.ui.notifications.NotificationsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject

class MainFragment : Fragment() {

    val navigator: Navigator by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if (savedInstanceState == null) {
            childFragmentManager
                .beginTransaction()
                .replace(R.id.main_fragment_container, HomeFragment())
                .commit()
        }

        val navView: BottomNavigationView = view.findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    navigator.addSubScreen(this, HomeFragment(), R.id.main_fragment_container)
                }
                R.id.navigation_dashboard -> {
                    navigator.addSubScreen(this, DashboardFragment(), R.id.main_fragment_container)
                }
                R.id.navigation_notifications -> {
                    navigator.addSubScreen(
                        this,
                        NotificationsFragment(),
                        R.id.main_fragment_container
                    )
                }
            }
            true
        }
    }

    companion object {
        fun create(): MainFragment {
            return MainFragment()
        }
    }
}