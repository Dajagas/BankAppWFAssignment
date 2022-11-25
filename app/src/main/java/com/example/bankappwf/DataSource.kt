package com.example.bankappwf

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

data class DataSource (val bankName: String, val accNo: Int, val accType: String)