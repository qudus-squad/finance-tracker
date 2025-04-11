package org.qudus.squad.logic.transactions

import org.qudus.squad.logic.FinanceTrackerDataSource
import org.qudus.squad.logic.models.Transaction

class EditTransactionDate (private val dataSource: FinanceTrackerDataSource) {

    fun editTransactionDate (transactionId: Int , newDate: Long): Boolean{

        val existingTransaction = dataSource.getTransactionById(transactionId)

        return if (existingTransaction != null) {
            // Only update the date field, keep the other fields intact
            val updatedTransaction = existingTransaction.copy(timeStamp = newDate)
            dataSource.updateTransaction(updatedTransaction)
            true
        } else {
            false
        }
    }
}