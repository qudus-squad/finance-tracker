package org.qudus.squad.logic.models

data class Transaction(
    val id: Int,
    val type: String,
    val amount: Double,
    val timeStamp: Long,
    val category: Category
)
