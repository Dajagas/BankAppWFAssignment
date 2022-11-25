package com.example.bankappwf

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts_table")
data class Account_Details(
    @PrimaryKey(autoGenerate = true)val id: Int?,
    @ColumnInfo(name = "bank_name") val bankName: String?,
    @ColumnInfo(name = "account_no") val accNo: Int?,
    @ColumnInfo(name = "account_type") val accType: String?,
)

