package com.marangoz.silo.models

import java.io.Serializable

data class Field(
    val field_id: Int,
    val fieldName: String,
    val fieldSize: String,
    val cropName: String,
    val cropAmount: Int,
    val cropSell: Float,
    val expenseAmount: Int
) : Serializable {

}


