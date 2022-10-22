package com.marangoz.silo.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context?) : SQLiteOpenHelper(context,"field.sqlite",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE field(field_id INTEGER PRIMARY KEY AUTOINCREMENT " +
                ",fieldName TEXT,fieldSize TEXT," +
                "cropName TEXT,cropAmount INTEGER,cropSell REAL,expenseAmount INTEGER);")

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS field")
        onCreate(db)
    }
}