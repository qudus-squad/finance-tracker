package org.qudus.squad.domain.models

import java.text.DateFormat

data class Transactions (
    val type : String,
    val amount : Double,
    val date  : DateFormat
   //val Category : List<categories>
)
