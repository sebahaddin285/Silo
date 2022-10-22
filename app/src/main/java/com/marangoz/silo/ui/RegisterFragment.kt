package com.marangoz.silo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.marangoz.silo.R
import com.marangoz.silo.data.DataBaseHelper
import com.marangoz.silo.data.fieldDao
import com.marangoz.silo.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {
    private lateinit var design: FragmentRegisterBinding
    var field_id: Int = 0
    lateinit var vt: DataBaseHelper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = FragmentRegisterBinding.inflate(inflater, container, false)
        vt = DataBaseHelper(requireActivity())
        val logo = design.toolbar.getChildAt(1)
        val updateIcon = design.toolbar.menu.findItem(R.id.updateIcon)
        val deleteIcon = design.toolbar.menu.findItem(R.id.deleteIcon)
        val bundle: RegisterFragmentArgs by navArgs()
        val cropSell = bundle.cropSell
        if (bundle.value == true) {
            updateIcon.setVisible(false)
            deleteIcon.setVisible(false)
        } else {
            design.saveButton.visibility = View.INVISIBLE
            field_id = bundle.fieldId
            design.fieldName.setText(bundle.fieldName)
            design.fieldSize.setText(bundle.fieldSize)
            design.cropName.setText(bundle.cropName)
            design.cropAmount.setText(bundle.cropAmount.toString())
            design.cropSell.setText(cropSell.toString())
            design.expenseAmount.setText(bundle.expenseAmount.toString())
        }




        logo.setOnClickListener() {
            pass()
        }
        design.toolbar.setOnMenuItemClickListener() {
            if (it.itemId == R.id.updateIcon) {
                if (isEmpty()) {
                    Toast.makeText(activity, "There are fields left blank", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    fieldDao().updateField(
                        vt,
                        field_id,
                        design.fieldName.text.toString(),
                        design.fieldSize.text.toString(),
                        design.cropName.text.toString(),
                        design.cropAmount.text.toString().toInt(),
                        design.cropSell.text.toString().toFloat(),
                        design.expenseAmount.text.toString().toInt()
                    )
                    Toast.makeText(activity, "Successful", Toast.LENGTH_SHORT).show()
                    pass()
                }
            } else if (it.itemId == R.id.deleteIcon) {
                fieldDao().deleteField(vt, field_id)
                Toast.makeText(activity, "Successful", Toast.LENGTH_SHORT).show()
                pass()
            }
            true
        }
        design.saveButton.setOnClickListener() {
            if (isEmpty()) {
                Toast.makeText(activity, "There are fields left blank", Toast.LENGTH_SHORT).show()
            } else {
                fieldDao().insertField(
                    vt,
                    design.fieldName.text.toString(),
                    design.fieldSize.text.toString(),
                    design.cropName.text.toString(),
                    design.cropAmount.text.toString().toInt(),
                    design.cropSell.text.toString().toFloat(),
                    design.expenseAmount.text.toString().toInt()
                )
                Toast.makeText(activity, "Successful", Toast.LENGTH_SHORT).show()
                pass()
            }

        }




        return design.root
    }

    fun isEmpty(): Boolean {
        var value = false
        if (design.fieldName.text?.isEmpty() == true || design.fieldSize.text?.isEmpty() == true
            || design.cropName.text?.isEmpty() == true || design.cropAmount.text?.isEmpty() == true ||
            design.cropSell.text?.isEmpty() == true || design.expenseAmount.text?.isEmpty() == true
        ) {
            value = true
        }
        return value
    }

    fun pass() {
        val pass = RegisterFragmentDirections.actionBack()
        Navigation.findNavController(design.root).navigate(pass)
    }


}