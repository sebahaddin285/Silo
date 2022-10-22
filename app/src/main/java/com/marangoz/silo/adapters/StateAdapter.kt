package com.marangoz.silo.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.marangoz.silo.R
import com.marangoz.silo.data.DataBaseHelper
import com.marangoz.silo.models.Field
import com.marangoz.silo.ui.HomePageFragmentDirections

class StateAdapter (val mContext : Context?,val fieldList:ArrayList<Field>):
    RecyclerView.Adapter<StateAdapter.ViewHolderClass>(){
    lateinit var design : View

    inner class ViewHolderClass(view: View) : RecyclerView.ViewHolder(view) {

       val cropNameText : TextView
       val incomeText : TextView
       val expenseText : TextView

        init {
            cropNameText = view.findViewById(R.id.cropNameT)
            incomeText = view.findViewById(R.id.incomeAmountText)
            expenseText = view.findViewById(R.id.expenseAmountText)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        design = LayoutInflater.from(mContext).inflate(R.layout.adapter_statement,parent,false)
        return ViewHolderClass(design)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val field = fieldList[position]

        holder.cropNameText.text = field.cropName
        holder.incomeText.text = (field.cropAmount * field.cropSell).toString() + " $"
        holder.expenseText.text = field.expenseAmount.toString() + " $"


    }

    override fun getItemCount(): Int {
      return fieldList.size
    }


}