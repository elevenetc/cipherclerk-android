package com.elevenetc.cipherclerk.android.details

import com.elevenetc.cipherclerk.android.common.Record
import com.elevenetc.cipherclerk.android.common.RecordsRepository
import com.elevenetc.cipherclerk.android.common.ViewModel
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: RecordsRepository) : ViewModel() {

    override fun onUserAction(action: UserAction) {
        when (action) {
            is GetRecord -> {
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
            is DeleteRecord -> {
                launch {
                    state.tryEmit(DeletingRecord(action.id))
                    repository.delete(action.id)
                    state.tryEmit(DeletedSuccessfully(action.id))
                }
            }
            is UpdateRecord -> {
                launch {
                    val record = action.updatedRecord
                    state.tryEmit(UpdatingRecord(action.id))
                    val updated = repository.update(record)
                    val get = repository.get(record.id)
                    state.tryEmit(RecordResult(get!!))
                }
            }
        }
    }

    /**
     * User actions
     */
    data class GetRecord(val id: Int) : UserAction()
    data class DeleteRecord(val id: Int) : UserAction()
    data class UpdateRecord(val id: Int, val updatedRecord: Record) : UserAction()

    /**
     * View states
     */
    data class RecordResult(val record: Record) : ViewState()
    data class RecordNotFoundResult(val id: Int) : ViewState()
    data class DeletingRecord(val id: Int) : ViewState()
    data class UpdatingRecord(val id: Int) : ViewState()
    data class DeletedSuccessfully(val id: Int) : ViewState()


}