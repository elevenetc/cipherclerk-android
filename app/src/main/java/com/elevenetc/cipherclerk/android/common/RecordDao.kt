package com.elevenetc.cipherclerk.android.common

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {
    @Query("SELECT * FROM records ORDER BY `key` ASC")
    suspend fun getAll(): List<Record>

    @Query("SELECT * FROM records ORDER BY `key` ASC")
    fun getAllFlow(): Flow<List<Record>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(record: Record): Long

    @Query("DELETE FROM records")
    suspend fun deleteAll()
}