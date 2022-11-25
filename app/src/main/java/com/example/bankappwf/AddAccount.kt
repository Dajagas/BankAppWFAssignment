package com.example.bankappwf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.bankappwf.databinding.FragmentAddAccountBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddAccount : Fragment() {

    private lateinit var bind : FragmentAddAccountBinding
    private lateinit var appDb : AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = FragmentAddAccountBinding.inflate(layoutInflater)
        var appDb = activity?.let { AppDatabase.getDatabase(it.applicationContext) }
        bind.spinner.adapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_dropdown_item,
            listOf("Axis","ICICI","HDFC"))

        fun writeData()
        {

            //val bankName = bankNameInter
            val bankName = bind.editTextBankName.text.toString()

            val accNo = bind.editTextAccNo.text.toString()
            val accNoConfirm = bind.editTextAccNoConfirm.text.toString()
            val accType = bind.editTextAccType.text.toString()
            //val bankName = bind.spinner.selectedItem.toString()

            println("LENGTH = "+accNo.length+" "+bankName)
            if (bankName.isNotEmpty() && accNo.isNotEmpty() && accNoConfirm.isNotEmpty() && accType.isNotEmpty())
            {
                println("HI THERE")
                if (accNo.length == 10 && accNoConfirm.length == 10 && accNo==accNoConfirm)
                {
                    println("PUDANG "+accNo::class.simpleName)
                    var accNo_temp:Int = Integer.parseInt(accNo)
                    val accountDetails = Account_Details(null,bankName,accNo_temp,accType)
                    GlobalScope.launch(Dispatchers.IO) {
                        if (appDb != null) {
                            appDb.AccountDetailsDao().insert(accountDetails)
                        }
                    }
                }
                bind.editTextBankName.text.clear()
                bind.editTextAccNo.text.clear()
                bind.editTextAccNoConfirm.text.clear()
                bind.editTextAccType.text.clear()

                Toast.makeText(activity,"Success",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(activity,"Failed",Toast.LENGTH_SHORT).show()
            }
        }

        bind.btnWriteData.setOnClickListener{
            writeData()
        }

        val view_acc_details_fragment = ViewAccountDetails()
        bind.btnReadData.setOnClickListener{
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frame_layout,view_acc_details_fragment).addToBackStack(null).commit()
            }
        }

        return bind.root
    }

}