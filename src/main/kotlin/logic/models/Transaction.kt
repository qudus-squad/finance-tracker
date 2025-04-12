package org.qudus.squad.logic.models

data class Transaction(
    val id: Int = TransactionIdGenerator.nextId(), //not nullable
    val type: TransactionType,
    val amount: Double,
    val timestamp: Long,
    val category: Category
)

object TransactionIdGenerator {
    private var counter = 0
    fun nextId(): Int = ++counter

    fun resetCounter(value: Int) {
        counter = value
    }

    fun getCurrentCounter(): Int = counter
}

enum class TransactionType {
    Deposit, Withdraw
}