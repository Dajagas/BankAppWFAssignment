package com.example.bankappwf

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.bankappwf.databinding.FragmentViewAccountDetailsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewAccountDetails : Fragment() {

    private lateinit var bind : FragmentViewAccountDetailsBinding
    private lateinit var appDb : AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = FragmentViewAccountDetailsBinding.inflate(layoutInflater)
        var appDb = activity?.let { AppDatabase.getDatabase(it.applicationContext) }
        // Inflate the layout for this fragment

        suspend fun displayData(accountDetails: Account_Details){
            withContext(Dispatchers.Main){
                bind.tvBankName.text = accountDetails.bankName
                bind.tvAccNo.text = accountDetails.accNo.toString()
                bind.tvAccType.text = accountDetails.accType
            }
        }

        fun readData(){
            val accNo = bind.editTextAccNoFind.text.toString()
            if (accNo.isNotEmpty())
            {
                lateinit var accountDetails : Account_Details
                GlobalScope.launch{
                    if (appDb != null) {
                        accountDetails = appDb.AccountDetailsDao().findByAccNo(accNo.toInt())
                    }
                    Log.d("GOMMA",accountDetails.toString())
                    displayData(accountDetails)
                }
            }
        }

        fun get_accs(){
            lateinit var acc_list : List<Account_Details>
            GlobalScope.launch{
                if (appDb != null) {
                    acc_list = appDb.AccountDetailsDao().getAll()
                }
                println("LIST SIZE "+acc_list.size)
                println("LIST: "+acc_list[0].accNo)
            }
        }

        bind.viewAccBtn.setOnClickListener {
            readData()
            get_accs()
        }

        return bind.root
        //return inflater.inflate(R.layout.fragment_view_account_details, container, false)
    }

}