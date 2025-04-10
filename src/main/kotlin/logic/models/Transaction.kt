package org.qudus.squad.logic.models

data class Transaction(
    val id: Int,
    var type: String,
    var amount: Double,
    var timeStamp: Long,
    var category: Category
)
{
}