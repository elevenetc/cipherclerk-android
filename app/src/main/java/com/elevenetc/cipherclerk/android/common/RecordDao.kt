package com.elevenetc.cipherclerk.android.common

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {
    @Query("SELECT * FROM records ORDER BY `key` ASC")
    suspend fun getAll(): List<Record>

    @Query("SELECT * FROM records WHERE id =:id")
    suspend fun get(id: Int): Record

    @Query("SELECT * FROM records ORDER BY `key` ASC")
    fun getAllFlow(): Flow<List<Record>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(record: Record): Long

    @Update
    suspend fun update(record: Record): Int

    @Query("DELETE FROM records WHERE id=:id")
    suspend fun delete(id: Int): Int

    @Query("DELETE FROM records")
    suspend fun deleteAll()
}