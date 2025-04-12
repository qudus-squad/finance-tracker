package org.qudus.squad.dataSource

import org.qudus.squad.logic.FinanceTrackerDataSource
import org.qudus.squad.logic.models.Category
import org.qudus.squad.logic.models.Transaction
import org.qudus.squad.logic.models.TransactionType

class FinanceTrackerDataSourceImpl : FinanceTrackerDataSource {

    private val _categories: MutableList<Category> =
        mutableListOf(Category(name = "Rent"), Category(name = "Salary"), Category(name = "Food"))
    private val transactions: MutableList<Transaction> = emptyList<Transaction>().toMutableList()
    val categoryList get() = this._categories

    init {
        FileDatabase.loadCounters()

        val loadedCategories = FileDatabase.loadCategories()
        if (loadedCategories.isNotEmpty()) {
            _categories.clear()
            _categories.addAll(loadedCategories)
        }

        val loadedTransactions = FileDatabase.loadTransactions(_categories)
        if (loadedTransactions.isNotEmpty()) {
            transactions.clear()
            transactions.addAll(loadedTransactions)
        }
    }

    override fun addCategory(category: Category): Boolean {
        if (category.name.isNotEmpty()) {
            this._categories.add(category)
            FileDatabase.saveCategories(_categories)
            FileDatabase.saveCounters()
            return true
        }
        return false
    }

    override fun removeCategory(categoryId: Int): Boolean {
        val result = this._categories.removeIf {
            it.id == categoryId
        }

        if (result) {
            FileDatabase.saveCategories(_categories)
        }

        return result
    }

    override fun getCategories(): List<Category> {
        return this._categories
    }

    override fun getCategoryById(categoryId: Int): Category? {
        return this._categories.find { it.id == categoryId }
    }

    override fun editExistingCategory(category: Category): Boolean {

        val index = categoryList.indexOfFirst { it.id == category.id }
        if (index.indexNotFound()) return false
        categoryList[index] = category
        FileDatabase.saveCategories(_categories)
        return true
    }

    override fun addNewTransaction(transaction: Transaction): Boolean {
        val result = transactions.add(transaction)

        // Save changes
        if (result) {
            FileDatabase.saveTransactions(transactions)
            FileDatabase.saveCounters()
        }

        return result
    }

    override fun removeTransaction(transactionId: Int): Boolean {
        val result = transactions.removeIf {
            it.id == transactionId
        }

        if (result) {
            FileDatabase.saveTransactions(transactions)
        }

        return result
    }

    override fun getAllTransactions(): List<Transaction> {
        return transactions
    }

    override fun getTransactionsByCategory(categoryId: Int): List<Transaction> {
        return transactions.filter { it.category.id == categoryId }
    }

    override fun getTransactionsInTimeRange(startTime: Long, endTime: Long): List<Transaction> {
        return transactions.filter { it.timestamp in startTime..endTime }
    }

    override fun getTransactionById(transactionId: Int): Transaction? {
        return transactions.find { it.id == transactionId }
    }

    override fun editExistingCategory(transaction: Transaction): Boolean {
        val index = transactions.indexOfFirst { it.id == transaction.id }
        if (index.indexNotFound()) return false

        transactions[index] = transaction

        FileDatabase.saveTransactions(transactions)

        return true
    }

    override fun getTotalExpenses(): Double {
        return transactions.filter { it.type == TransactionType.Withdraw }.sumOf { it.amount }
    }

    override fun getTotalIncome(): Double {
        return transactions.filter { it.type == TransactionType.Deposit }.sumOf { it.amount }
    }

    override fun getBalance(): Double {
        return getTotalIncome() - getTotalExpenses()
    }
}

private fun Int.indexNotFound() = this == -1