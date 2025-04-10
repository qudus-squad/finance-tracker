package org.qudus.squad.logic.statements

import org.qudus.squad.logic.FinanceTrackerDataSource
import org.qudus.squad.logic.models.Transaction

abstract class ShowRecentTransactions (private val dataSource: FinanceTrackerDataSource) {

    fun showTransactions():List<Transaction> {
       return dataSource.getAllTransactions()
    }

}