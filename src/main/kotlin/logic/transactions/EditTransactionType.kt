package org.qudus.squad.logic.transactions

import org.qudus.squad.logic.FinanceTrackerDataSource

class EditTransactionType(
    val datastore: FinanceTrackerDataSource
) {
    fun editTransactionCategory(transactionId: Int, transactionType: String): Boolean {
        val userTransaction = datastore.getTransactionById(transactionId)

        if (userTransaction != null) {
            userTransaction.type = transactionType
            return true
        }
        return false
    }
}