package com.example.bankappwf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.bankappwf.databinding.FragmentAccountsBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Accounts : Fragment(),AccountClickHandler {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        val bind = FragmentAccountsBinding.inflate(layoutInflater)
        var appDb = activity?.let { AppDatabase.getDatabase(it.applicationContext) }
        val add_acc_fragment = AddAccount()
        val view_acc_fragment = ViewAccountDetails()

        fun getAccs(): List<Account_Details> {
            lateinit var acc_list : List<Account_Details>
            //GlobalScope.launch{
                if (appDb != null) {
                    acc_list = appDb.AccountDetailsDao().getAll()
                }
                println("LIST SIZE "+acc_list.size)
                println("LIST: "+acc_list[0].accNo)
            //}
            return acc_list
        }

        val args = this.arguments
        val maskstate = args?.get("maskState").toString().toBoolean()
        println("MASK STATE FROM INSIDE ACCOUNTS $maskstate")

        var accsList = getAccs()
        val recyclerView = bind.rvAccDetails
        recyclerView.adapter = AccountsAdapter(accsList,this,maskstate)
        recyclerView.setHasFixedSize(true)

        bind.addAccBtn.setOnClickListener{
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frame_layout,add_acc_fragment).addToBackStack(null).commit()
            }

        }

        bind.accListBtn.setOnClickListener{
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frame_layout,view_acc_fragment).addToBackStack(null).commit()
            }

        }

        return bind.root
    }

    override fun onClickHandler(acc: Account_Details) {
        val view_account_fragment = ViewAccount()
        println("POTATO TIME ${acc.accNo}")
        val bundle = Bundle()
        bundle.putInt("account_number",acc.accNo!!)
        view_account_fragment.arguments = bundle
        //Toast.makeText(activity,"SIKE CLICK WORKS ${acc.accNo}",Toast.LENGTH_SHORT).show()
        fragmentManager?.beginTransaction()?.apply {
            replace(R.id.frame_layout,view_account_fragment).addToBackStack(null).commit()
        }
    }

}