package org.qudus.squad.dataSource

import org.qudus.squad.logic.FinanceTrackerDataSource
import org.qudus.squad.logic.models.Category
import org.qudus.squad.logic.models.Transaction

class FinanceTrackerDataSourceImpl : FinanceTrackerDataSource {

    private val categories: MutableList<Category> = emptyList<Category>().toMutableList()
    private val transactions: MutableList<Transaction> = emptyList<Transaction>().toMutableList()

    override fun addCategory(category: Category): Boolean {
        if (category.name.isNotEmpty()) {
            categories.add(category)
            return true
        }
        return false
    }

    override fun removeCategory(categoryId: Int): Boolean {
        return categories.removeIf {
            it.id == categoryId
        }
    }

    override fun getCategories(): List<Category> {
        return categories
    }

    override fun getCategoryById(categoryId: Int): Category? {
        return categories.find { it.id == categoryId }
    }

    override fun updateCategory(category: Category): Boolean {
        val index = categories.indexOfFirst { it.id == category.id }
        return if (index != -1) {
            categories[index] = category
            true
        } else {
            false
        }
    }

    override fun addTransaction(transaction: Transaction): Boolean {
        return transactions.add(transaction)
    }

    override fun removeTransaction(transactionId: Int): Boolean {
        return transactions.removeIf {
            it.id == transactionId
        }
    }

    override fun getAllTransactions(): List<Transaction> {
        return transactions
    }

    override fun getTransactionsByCategory(categoryId: Int): List<Transaction> {
        return transactions.filter { it.category.id == categoryId }
    }

    override fun getTransactionsInTimeRange(startTime: Long, endTime: Long): List<Transaction> {
        return transactions.filter { it.timeStamp in startTime..endTime }
    }

    override fun getTransactionById(transactionId: Int): Transaction? {
        return transactions.find { it.id == transactionId }
    }

    override fun updateTransaction(transaction: Transaction): Boolean {
        val index = transactions.indexOfFirst { it.id == transaction.id }
        return if (index != -1) {
            transactions[index] = transaction
            true
        } else {
            false
        }
    }

    override fun getTotalExpenses(): Double {
        return transactions.filter { it.amount < 0 }.sumOf { it.amount }
    }

    override fun getTotalIncome(): Double {
        return transactions.filter { it.amount > 0 }.sumOf { it.amount }
    }

    override fun getBalance(): Double {
        return getTotalIncome() + getTotalExpenses()
    }
}