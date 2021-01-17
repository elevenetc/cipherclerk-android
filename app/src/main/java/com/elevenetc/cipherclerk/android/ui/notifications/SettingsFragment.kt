package com.elevenetc.cipherclerk.android.ui.notifications

import android.os.Bundle
import android.view.View
import com.elevenetc.cipherclerk.android.R
import com.elevenetc.cipherclerk.android.common.BaseFragment
import com.elevenetc.cipherclerk.android.common.ViewModel
import com.elevenetc.cipherclerk.android.lock.LockFragment
import com.elevenetc.cipherclerk.android.navigation.Navigator
import com.elevenetc.cipherclerk.android.ui.notifications.SettingsViewModel.Lock
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    private val vm: SettingsViewModel by inject()
    private val navigator: Navigator by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        launch {
            vm.state.collect { state ->
                handleState(state)
            }
        }

        view.findViewById<View>(R.id.btn_lock).setOnClickListener {
            vm.onUserAction(Lock)
        }

        view.findViewById<View>(R.id.btn_clean).setOnClickListener {

        }
    }

    override fun handleState(state: ViewModel.ViewState) {
        if (state is SettingsViewModel.Locked) {
            navigator.replaceRootScreen(LockFragment())
        }
    }
}