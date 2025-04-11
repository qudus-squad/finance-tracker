package org.qudus.squad.logic.models

data class Transaction(
    val id: Int? = TransactionIdGenerator.nextId(),
    var type: TransactionType,
    var amount: Double,
    var timeStamp: Long,
    var category: Category
)

object TransactionIdGenerator {
    private var counter = 0
    fun nextId(): Int = ++counter
}

enum class TransactionType {
    Deposit , Withdraw
}


