package com.elevenetc.cipherclerk.android.details

import com.elevenetc.cipherclerk.android.common.Record
import com.elevenetc.cipherclerk.android.common.RecordsRepository
import com.elevenetc.cipherclerk.android.common.ViewModel
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: RecordsRepository) : ViewModel() {

    override fun onUserAction(action: UserAction) {
        if (action is GetRecord) {
            launch {
                val recordId = action.id
                val record = repository.get(recordId)
                if (record == null) {
                    state.tryEmit(RecordNotFoundResult(recordId))
                } else {
                    state.tryEmit(RecordResult(record))
                }

            }
        }
    }

    data class GetRecord(val id: Int) : UserAction()
    data class RecordResult(val record: Record) : ViewState()
    data class RecordNotFoundResult(val id: Int) : ViewState()


}