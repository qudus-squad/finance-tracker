package org.qudus.squad.dataSource

import org.qudus.squad.logic.FinanceTrackerDataSource
import org.qudus.squad.logic.models.Category
import org.qudus.squad.logic.models.Transaction

class InMemoryStorage : FinanceTrackerDataSource {

    private val transactions = mutableListOf<Transaction>()

    override fun addTransaction(transaction: Transaction): Boolean {
        transactions.add(transaction)
        return true
    }

    override fun getAllTransactions(): List<Transaction> {
        return transactions
    }

    fun clearTransactions() {
        transactions.clear()
    }

    override fun removeTransaction(transactionId: Int) = TODO()
    override fun getTransactionsByCategory(categoryId: Int) = TODO()
    override fun getTransactionsInTimeRange(startTime: Long, endTime: Long) = TODO()
    override fun getTransactionById(transactionId: Int) = TODO()
    override fun updateTransaction(transaction: Transaction) = TODO()
    override fun addCategory(category: Category) = TODO()
    override fun removeCategory(categoryId: Int) = TODO()
    override fun getCategories() = TODO()
    override fun getCategoryById(categoryId: Int) = TODO()
    override fun updateCategory(category: Category) = TODO()
    override fun getTotalExpenses() = TODO()
    override fun getTotalIncome() = TODO()
    override fun getBalance() = TODO()
}