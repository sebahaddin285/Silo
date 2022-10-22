package com.marangoz.silo.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.marangoz.silo.adapters.FieldAdapter
import com.marangoz.silo.adapters.StateAdapter
import com.marangoz.silo.data.DataBaseHelper
import com.marangoz.silo.data.fieldDao
import com.marangoz.silo.databinding.FragmentStatementBinding
import com.marangoz.silo.models.Field


class StatementFragment : Fragment() {

    private lateinit var design: FragmentStatementBinding
    lateinit var adapter: StateAdapter
    lateinit var fieldList : ArrayList<Field>
    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = FragmentStatementBinding.inflate(inflater,container,false)

        val vt = DataBaseHelper(requireActivity())
        fieldList = ArrayList()

        design.rv1.setHasFixedSize(true)
        design.rv1.layoutManager = LinearLayoutManager(activity)

        fieldList = fieldDao().allFields(vt)

        adapter = StateAdapter(activity,fieldList)
        design.rv1.adapter = adapter

        var income =0.0f
        var expense=0.0f
        for (k in fieldList){
            income += (k.cropAmount * k.cropSell)
            expense += k.expenseAmount
        }
        design.incomeText.text = income.toString() + " $"
        design.expenseText.text = expense.toString() + " $"
        design.profitText.text = (income - expense).toString() + " $"

        return design.root
    }


}