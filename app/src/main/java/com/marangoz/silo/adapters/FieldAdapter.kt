package com.marangoz.silo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.marangoz.silo.R
import com.marangoz.silo.data.DataBaseHelper
import com.marangoz.silo.models.Field
import com.marangoz.silo.ui.HomePageFragmentDirections


class FieldAdapter(val mContext: Context?, val fieldList: ArrayList<Field>) :
    RecyclerView.Adapter<FieldAdapter.ViewHolderClass>() {
    lateinit var design: View

    inner class ViewHolderClass(view: View) : RecyclerView.ViewHolder(view) {

        val fieldNameText: TextView
        val fieldSizeText: TextView
        val cardView: CardView
        val cropNameText: TextView

        init {

            fieldNameText = view.findViewById(R.id.cropNameT)
            fieldSizeText = view.findViewById(R.id.fieldSizeText)
            cardView = view.findViewById(R.id.cardView)
            cropNameText = view.findViewById(R.id.incomeAmountText)


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        design = LayoutInflater.from(mContext).inflate(R.layout.adapter_design, parent, false)
        return ViewHolderClass(design)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val vt = DataBaseHelper(mContext)
        val field = fieldList[position]
        holder.fieldNameText.text = field.fieldName
        holder.fieldSizeText.text = field.fieldSize + " Acre"
        holder.cropNameText.text = field.cropName
        try {
            holder.cardView.setOnClickListener() {
                val pass = HomePageFragmentDirections.goToRegister(
                    false,
                    field.field_id,
                    field.fieldName,
                    field.fieldSize,
                    field.cropName,
                    field.cropAmount,
                    field.cropSell,
                    field.expenseAmount
                )
                Navigation.findNavController(design).navigate(pass)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    override fun getItemCount(): Int {
        return fieldList.size
    }


}