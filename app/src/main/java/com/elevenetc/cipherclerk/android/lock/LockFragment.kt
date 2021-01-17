package com.elevenetc.cipherclerk.android.lock

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.elevenetc.cipherclerk.android.R
import com.elevenetc.cipherclerk.android.common.ViewModel.ViewState
import com.elevenetc.cipherclerk.android.home.HomeFragment
import com.elevenetc.cipherclerk.android.lock.LockViewModel.*
import com.elevenetc.cipherclerk.android.navigation.Navigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import kotlin.coroutines.CoroutineContext

class LockFragment : Fragment(R.layout.fragment_lock), CoroutineScope {

    val vm: LockViewModel by inject()
    val navigator: Navigator by inject()

    //lateinit var editPassword: EditText
    lateinit var stateView: LockStateView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        launch {
            vm.onUserAction(GetLockState)
            vm.state.collect { state ->
                handleState(state)
            }
        }

        stateView = view.findViewById(R.id.lock_state_view)

//        editPassword = view.findViewById(R.id.edit_password)
//        editPassword.doAfterTextChanged {
//            val chars = it!!.chars()
//            vm.onUserAction(PasswordEntry(chars.toArray().map { intCh -> intCh.toChar() }))
//        }
//
//        editPassword.setOnKeyListener { _, keyCode, event ->
//            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
//                vm.onUserAction(Next)
//                true
//            }
//            false
//        }

//        stateView.setOnKeyListener(object : View.OnKeyListener {
//            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
//                if (keyCode == KeyEvent.KEYCODE_ENTER) {
//
//                } else {
//
//                }
//
//                return true
//            }
//        })

        stateView.userActionListener = { action ->
            vm.onUserAction(action)
        }
    }

    override fun onResume() {
        super.onResume()
        stateView.showKey()
    }

    private fun handleState(state: ViewState) {

        stateView.handleState(state)

        if (state is LockCreated) {
            navigator.addRootScreen(HomeFragment.create(), false)
        }

        //val stateView = view.findViewById<LockStateView>(R.id.lock_state_view)
        //val keyboardView = view.findViewById<LockKeyboard>(R.id.lock_keyboard)

        //stateView.textState.text = state.javaClass.simpleName

        if (state is Unlocked) {
            //navigator.
        } else if (state is CreatingLock) {
            //editPassword.setText(state.lock.toString())
        } else if (state is CreatingLockVerify) {
            //editPassword.setText(state.verify.toString())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}