package org.qudus.squad.data.entity

import java.text.DateFormat

data class TransactoinsEntity (
    val  amount : Double,
    val type: String,
    val date : DateFormat,
    val secondDate : DateFormat, // when transaction date is edited
    //val category : List<category>
)