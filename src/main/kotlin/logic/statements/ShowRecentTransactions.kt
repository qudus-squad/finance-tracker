package org.qudus.squad.logic.statements

import org.qudus.squad.logic.FinanceTrackerDataSource
import org.qudus.squad.logic.models.Transaction

class ShowRecentTransactions (private val dataSource: FinanceTrackerDataSource) {

    fun showLastFiveRecentTransactions():List<Transaction> {
       return dataSource.getAllTransactions()
    }

}