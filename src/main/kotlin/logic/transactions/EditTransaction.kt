package org.qudus.squad.logic.transactions
import org.qudus.squad.Utilities
import org.qudus.squad.logic.FinanceTrackerDataSource
import org.qudus.squad.logic.models.Category
import org.qudus.squad.logic.models.TransactionType

class EditTransaction(
    private val datastore: FinanceTrackerDataSource
) {
    fun editTransactionAmount(transactionId: Int, transactionAmount: Double): Boolean {
        if (isValidAmount(transactionAmount)) {
            val currentTransaction = datastore.getTransactionById(transactionId)

            if (currentTransaction != null) {
                val editedTransaction = currentTransaction.copy(amount = transactionAmount)
                return datastore.updateTransaction(editedTransaction)
            }
        }
        return false
    }

    fun editTransactionTimeStamp(transactionId: Int, newTimestamp: String): Boolean {
        val parts = newTimestamp.split("-")
        if (parts.size != 3) return false

        val day = parts[0].toInt()
        val month = parts[1].toInt()
        val year = parts[2].toInt()

        if (day !in 1..31 || month !in 1..12 || year !in 1..2025) return false

        val currentTransaction = datastore.getTransactionById(transactionId)
        if (currentTransaction != null) {
            val newTimestampLong = Utilities.parseDateStringToTimestamp(newTimestamp)
            val editedTransaction = currentTransaction.copy(timestamp = newTimestampLong)
            return datastore.updateTransaction(editedTransaction)
        }
        return false
    }

    fun editTransactionCategory(transactionId: Int, category: Category): Boolean {

        val currentTransaction = datastore.getTransactionById(transactionId)

        if (currentTransaction != null) {
            val editedTransaction = currentTransaction.copy(category = category)
            datastore.updateTransaction(editedTransaction)
        }
        return false
    }

    fun editTransactionType(transactionId: Int, transactionType: TransactionType): Boolean {
        val currentTransaction = datastore.getTransactionById(transactionId)

        if (currentTransaction != null) {
            val editedTransaction = currentTransaction.copy(type = transactionType)
            datastore.updateTransaction(editedTransaction)
        }
        return false
    }


    private fun isValidAmount(transactionAmount: Double): Boolean {
        if (transactionAmount < 0) return false
        return true
    }



}