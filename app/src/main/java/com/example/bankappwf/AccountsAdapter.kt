package com.example.bankappwf

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class AccountsAdapter(val dataset:List<Account_Details>, val clickHandler: AccountClickHandler, val maskState:Boolean) : RecyclerView.Adapter<AccountsAdapter.AccountsViewHolder>(){

    inner class AccountsViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{
        val accNo =itemView.findViewById<TextView>(R.id.tv_acc_number)

        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val curr_acc = dataset[adapterPosition]
            clickHandler.onClickHandler(curr_acc)
        }
        val btn = itemView.findViewById<Button>(R.id.delete_acc_btn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountsViewHolder {
        return AccountsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_acc_details, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AccountsViewHolder, position: Int) {
        val accNo = dataset[position].accNo.toString()
        if(maskState){
            val accNoLast = accNo.subSequence(6,9)
            val accDisplay = "XXXXXX"+accNoLast
            holder.accNo.text = accDisplay
        }
        else{
            holder.accNo.text = accNo
        }
        //holder.accNo.text = dataset[position].accNo.toString()
        println("AYE BRO SEE THIS "+dataset[position].accNo.toString())
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}