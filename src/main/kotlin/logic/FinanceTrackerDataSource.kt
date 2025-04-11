package org.qudus.squad.logic

import org.qudus.squad.logic.models.Category
import org.qudus.squad.logic.models.Transaction

interface FinanceTrackerDataSource {

    //Category Methods
    fun addCategory(category: Category): Boolean
    fun removeCategory(categoryId: Int): Boolean
    fun getCategories(): List<Category>
    fun getCategoryById(categoryId: Int): Category?
    fun updateCategory(category: Category): Boolean

    // Transaction methods
    fun addNewTransaction(transaction: Transaction): Boolean
    fun removeTransaction(transactionId: Int): Boolean
    fun getAllTransactions(): List<Transaction>
    fun getTransactionsByCategory(categoryId: Int): List<Transaction>
    fun getTransactionsInTimeRange(startTime: Long, endTime: Long): List<Transaction>
    fun getTransactionById(transactionId: Int): Transaction?
    fun updateTransaction(transaction: Transaction): Boolean

    // Utility methods
    fun getTotalExpenses(): Double
    fun getTotalIncome(): Double
    fun getBalance(): Double

}