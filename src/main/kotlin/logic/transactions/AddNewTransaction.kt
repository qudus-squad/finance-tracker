package org.qudus.squad.logic.transactions

import org.qudus.squad.logic.FinanceTrackerDataSource
import org.qudus.squad.logic.models.Transaction


class AddNewTransaction(
    private val dataSource: FinanceTrackerDataSource
) {




    fun CompleteTransaction(transaction: Transaction) {
        if (isAmountValid(transaction)) {
            dataSource.addNewTransaction(transaction)
        }
    }

    fun isAmountValid(transaction: Transaction): Boolean = transaction.amount < 0

}

