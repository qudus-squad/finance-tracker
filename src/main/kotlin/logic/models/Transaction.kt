package org.qudus.squad.logic.models

data class Transaction(
    val id: Int = TransactionIdGenerator.nextId(), //not nullable
    val type: TransactionType,
    val amount: Double,
    val timeStamp: Long,
    val category: Category
)

object TransactionIdGenerator {
    private var counter = 0
    fun nextId(): Int = ++counter
}

enum class TransactionType {
    Deposit, Withdraw
}