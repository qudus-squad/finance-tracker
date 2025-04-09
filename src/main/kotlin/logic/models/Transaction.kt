package org.qudus.squad.logic.models

data class Transaction(
    val type: String,
    val amount: Double,
    val timeStamp: Long,
    val category: List<String>,
)
