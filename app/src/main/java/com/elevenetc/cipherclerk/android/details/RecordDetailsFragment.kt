package com.elevenetc.cipherclerk.android.details

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.elevenetc.cipherclerk.android.R
import com.elevenetc.cipherclerk.android.common.ViewModel.Loading
import com.elevenetc.cipherclerk.android.common.ViewModel.ViewState
import com.elevenetc.cipherclerk.android.details.DetailsViewModel.*
import com.elevenetc.cipherclerk.android.navigation.Navigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import kotlin.coroutines.CoroutineContext

class RecordDetailsFragment : Fragment(R.layout.fragment_record_details), CoroutineScope {

    val vm: DetailsViewModel by inject()
    val navigator: Navigator by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val id = requireArguments().getInt("id")

        launch {
            vm.onUserAction(GetRecord(id))
            vm.state.collect { state ->
                handleState(state)
            }
        }
    }

    private fun handleState(state: ViewState) {
        if (state is Loading || state is DeletingRecord) {
            view?.findViewById<View>(R.id.text_loading)?.visibility = View.VISIBLE
            view?.findViewById<View>(R.id.content_container)?.visibility = View.GONE
        } else if (state is RecordResult) {
            val record = state.record
            val editValue = view?.findViewById<TextView>(R.id.edit_text_value)!!

            editValue.text = record.value

            view?.findViewById<View>(R.id.text_loading)?.visibility = View.GONE
            view?.findViewById<View>(R.id.content_container)?.visibility = View.VISIBLE
            view?.findViewById<TextView>(R.id.text_id)?.text = record.id.toString()
            view?.findViewById<TextView>(R.id.text_key)?.text = record.key

            view?.findViewById<TextView>(R.id.btn_delete)?.setOnClickListener {
                vm.onUserAction(DeleteRecord(record.id))
            }

            view?.findViewById<TextView>(R.id.btn_update)?.setOnClickListener {
                val newValue = editValue.text.toString()
                vm.onUserAction(UpdateRecord(record.id, record.copy(value = newValue)))
            }

        } else if (state is DeletedSuccessfully) {
            navigator.goBack()
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