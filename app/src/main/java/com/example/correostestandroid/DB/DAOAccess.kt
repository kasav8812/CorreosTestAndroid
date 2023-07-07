package com.example.correostestandroid.DB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.correostestandroid.Adapters.MailEntity

@Dao
interface DAOAccess {

    @Query("SELECT * FROM Mail")
    fun retrieveMails() : LiveData<List<MailEntity>>

    @Query("delete FROM Mail")
    suspend fun deleteData()

    @Query("delete from Mail WHERE id = :emailId")
    fun deleteEmail(emailId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(mailTable: List<MailEntity>)


}