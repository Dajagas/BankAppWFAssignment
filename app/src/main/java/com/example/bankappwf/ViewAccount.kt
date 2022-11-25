package com.example.bankappwf

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bankappwf.databinding.FragmentViewAccountBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewAccount : Fragment() {

    private lateinit var binding : FragmentViewAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewAccountBinding.inflate(layoutInflater)
        var appDb = activity?.let { AppDatabase.getDatabase(it.applicationContext) }
        val args = this.arguments
        val account_number:Int = args?.get("account_number").toString().toInt()
        println("TOMATO BATH $account_number")

        suspend fun displayData(accountDetails: Account_Details){
            withContext(Dispatchers.Main){
                binding.tvBankNameNew.text = accountDetails.bankName
                binding.tvAccNumberNew.text = accountDetails.accNo.toString()
                binding.tvAccTypeNew.text = accountDetails.accType
            }
        }
        fun readData(){
            if (account_number!=null)
            {
                lateinit var accountDetails : Account_Details
                GlobalScope.launch{
                    if (appDb != null) {
                        accountDetails = appDb.AccountDetailsDao().findByAccNo(account_number)
                    }
                    println("PEAS PULAO $accountDetails.toString()")
                    Log.d("GOMMA",accountDetails.toString())
                    displayData(accountDetails)
                }
            }
        }

        readData()

        return binding.root
        //return inflater.inflate(R.layout.fragment_view_account, container, false)
    }

}