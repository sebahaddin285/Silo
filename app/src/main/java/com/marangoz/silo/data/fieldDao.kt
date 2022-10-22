package com.marangoz.silo.data

import android.annotation.SuppressLint
import android.content.ContentValues
import com.marangoz.silo.models.Field

class fieldDao {

    fun insertField(vt: DataBaseHelper,fieldName:String,fieldSize:String,cropName:String,cropAmount:Int,cropSell:Float,expenseAmount:Int){
        val dbx = vt.writableDatabase
        val values = ContentValues()
        values.put("fieldName",fieldName)
        values.put("fieldSize",fieldSize)
        values.put("cropName",cropName)
        values.put("cropAmount",cropAmount)
        values.put("cropSell",cropSell)
        values.put("expenseAmount",expenseAmount)
        dbx.insertOrThrow("field",null,values)
        dbx.close()
    }
    fun updateField(vt: DataBaseHelper,field_id: Int,fieldName:String,fieldSize:String,cropName:String,cropAmount:Int,cropSell:Float,expenseAmount:Int){
        val dbx = vt.writableDatabase
        val values = ContentValues()
        values.put("fieldName",fieldName)
        values.put("fieldSize",fieldSize)
        values.put("cropName",cropName)
        values.put("cropAmount",cropAmount)
        values.put("cropSell",cropSell)
        values.put("expenseAmount",expenseAmount)
        dbx.update("field",values,"field_id=?", arrayOf(field_id.toString()))
        dbx.close()
    }

    fun deleteField(vt: DataBaseHelper,field_id: Int){
        val dbx = vt.writableDatabase

        dbx.delete("field","field_id=?", arrayOf(field_id.toString()))
        dbx.close()

    }

    @SuppressLint("Range")
    fun allFields(vt : DataBaseHelper?) : ArrayList<Field>{

        val fieldList = ArrayList<Field>()
        val db  = vt?.writableDatabase

        val cursor  = db?.rawQuery("SELECT * FROM field ",null )

        if (cursor != null) {
            while (cursor.moveToNext()){
                val field  = Field(cursor.getInt(cursor.getColumnIndex("field_id"))
                    ,cursor.getString(cursor.getColumnIndex("fieldName"))
                    ,cursor.getString(cursor.getColumnIndex("fieldSize"))
                    ,cursor.getString(cursor.getColumnIndex("cropName"))
                    ,cursor.getInt(cursor.getColumnIndex("cropAmount"))
                    ,cursor.getFloat(cursor.getColumnIndex("cropSell"))
                    ,cursor.getInt(cursor.getColumnIndex("expenseAmount")))

                fieldList.add(field) // tabloda ki verileri tek tek array liste kaydetmiş olduk
            }
        }

        return fieldList
    }




}