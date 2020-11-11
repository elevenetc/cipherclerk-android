package com.elevenetc.cipherclerk.android.common

class RecordsRepository(private val recordDao: RecordDao) {

    val allRecords = recordDao.getAllFlow()

    suspend fun insert(record: Record) {
        recordDao.insert(record)
    }
}