package com.elevenetc.cipherclerk.android.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.elevenetc.cipherclerk.android.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import kotlin.coroutines.CoroutineContext

class RecordDetailsFragment : Fragment(R.layout.fragment_record_details), CoroutineScope {

    val vm: DetailsViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val id = requireArguments().getInt("id")

        launch {
            vm.state.collect {
                println(it)
            }
            vm.onUserAction(DetailsViewModel.GetRecord(id))
        }
        
        view.findViewById<View>(R.id.btn_delete).setOnClickListener {

        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    companion object {
        fun create(id: Int): RecordDetailsFragment {
            return RecordDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt("id", id)
                }
            }
        }
    }
}