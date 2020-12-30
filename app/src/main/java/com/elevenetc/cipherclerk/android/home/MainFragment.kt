package com.elevenetc.cipherclerk.android.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.elevenetc.cipherclerk.android.R
import com.elevenetc.cipherclerk.android.ui.dashboard.DashboardFragment
import com.elevenetc.cipherclerk.android.ui.notifications.NotificationsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment : Fragment() {

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
                    childFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_fragment_container, HomeFragment())
                        .commit()
                }
                R.id.navigation_dashboard -> {
                    childFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_fragment_container, DashboardFragment())
                        .commit()
                }
                R.id.navigation_notifications -> {
                    childFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_fragment_container, NotificationsFragment())
                        .commit()
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