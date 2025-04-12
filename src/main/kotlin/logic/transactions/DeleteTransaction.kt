package org.qudus.squad.logic.transactions
import org.qudus.squad.logic.FinanceTrackerDataSource

class DeleteTransaction(private val dataSource: FinanceTrackerDataSource) {
    fun deleteTransaction(transactionId: Int): Boolean {
        val transaction = dataSource.getTransactionById(transactionId)
        return if (transaction == null) {
            false
        } else dataSource.removeTransaction(transactionId)
    }
}