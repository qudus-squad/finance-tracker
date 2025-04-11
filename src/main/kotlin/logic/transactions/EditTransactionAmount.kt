package org.qudus.squad.logic.transactions

import org.qudus.squad.logic.FinanceTrackerDataSource

class EditTransactionAmount(
    private val datastore: FinanceTrackerDataSource
) {
    fun editTransactionAmount(transactionId: Int, transactionAmount: Double): Boolean {
        val userTransaction = datastore.getTransactionById(transactionId)
        if (userTransaction != null) {
            userTransaction.amount = transactionAmount
            return true
        }
        if(transactionAmount < 0) return false

        return false
    }
}