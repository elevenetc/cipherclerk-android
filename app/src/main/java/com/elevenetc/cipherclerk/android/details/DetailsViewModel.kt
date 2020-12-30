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
        } else if (action is DeleteRecord) {
            launch {
                state.tryEmit(DeletingRecord(action.id))
                repository.delete(action.id)
                state.tryEmit(DeletedSuccessfully(action.id))
            }
        }
    }

    data class GetRecord(val id: Int) : UserAction()
    data class DeleteRecord(val id: Int) : UserAction()

    data class RecordResult(val record: Record) : ViewState()
    data class RecordNotFoundResult(val id: Int) : ViewState()
    data class DeletingRecord(val id: Int) : ViewState()
    data class DeletedSuccessfully(val id: Int) : ViewState()


}