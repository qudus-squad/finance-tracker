package org.qudus.squad.logic.statements


import org.qudus.squad.logic.FinanceTrackerDataSource
import org.qudus.squad.logic.models.Category

class GetFinancialTrackerDetails(
    private val dataSource: FinanceTrackerDataSource
) {
    fun getBalance(): Double {
        val balance = dataSource.getBalance()
        return balance
    }

    fun getAllCategories(): List<Category> {
        val categories = dataSource.getCategories()
        return categories
    }
}