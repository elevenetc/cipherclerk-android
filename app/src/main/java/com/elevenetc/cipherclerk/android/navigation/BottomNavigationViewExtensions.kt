package com.elevenetc.cipherclerk.android.navigation

import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.initNavigation(
    map: Map<Int, Fragment>,
    container: Int,
    parent: Fragment
) {

    setOnNavigationItemSelectedListener { item ->
        val itemId = item.itemId
        val newFragment = map[itemId] ?: error("Undefined fragment id: $itemId")
        val fm = parent.childFragmentManager

        val currentFragment = fm.getVisible()

        //TODO: add error message if currentFragment is null

        var transaction = fm.beginTransaction().hide(currentFragment!!)

        transaction = if (newFragment.isAdded) {
            transaction.show(newFragment)
        } else {
            transaction.add(container, newFragment)
        }

        transaction.commit()

        true
    }
}