package com.example.bankappwf

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//used to write queries
@Dao
interface AccountDetailsDao {
    @Query("SELECT * FROM accounts_table")
    fun getAll():List<Account_Details>

    @Query("SELECT * FROM accounts_table WHERE account_no LIKE :accNo LIMIT 1")
    suspend fun findByAccNo(accNo: Int): Account_Details

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(accdetails: Account_Details)

   // @Query("DELETE FROM accounts_table WHERE account_no LIKE :accNo LIMIT 1")
    //suspend fun deleteByAccNo(accNo: Int): Account_Details

    //@Delete
    //suspend fun delete(accdetails: Account_Details)

    //@Query("DELETE FROM accounts_table")
    //suspend fun deleteAll()
}