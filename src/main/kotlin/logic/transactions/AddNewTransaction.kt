package org.qudus.squad.logic.transactions
import org.qudus.squad.logic.FinanceTrackerDataSource
import org.qudus.squad.logic.models.Transaction

class AddNewTransaction(
    private val dataSource: FinanceTrackerDataSource
) {
    fun addNewTransaction(transaction: Transaction):Boolean {
        return if (isAmountValid(transaction)) {
            dataSource.addNewTransaction(transaction)
         }else false
    }
    fun isAmountValid(transaction: Transaction): Boolean = transaction.amount > 0
}

