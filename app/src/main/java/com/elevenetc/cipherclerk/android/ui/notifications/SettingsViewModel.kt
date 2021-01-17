package com.elevenetc.cipherclerk.android.ui.notifications

import com.elevenetc.cipherclerk.android.common.LockRepository
import com.elevenetc.cipherclerk.android.common.ViewModel

class SettingsViewModel(val lockRepository: LockRepository) : ViewModel() {

    override fun onUserAction(action: UserAction) {
        if (action is Lock) {
            lockRepository.lock()
            state.tryEmit(Locked)
        }
    }

    object Locked : ViewState()

    object Lock : UserAction()
}