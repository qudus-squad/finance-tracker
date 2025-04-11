package org.qudus.squad.logic.transactions

import org.qudus.squad.logic.FinanceTrackerDataSource

class EditTransactionCategory(
    private val datastore: FinanceTrackerDataSource
) {
    fun editTransactionCategory(transactionId: Int, transactionCategoryName: String, id: Int): Boolean {
        val userTransaction = datastore.getTransactionById(transactionId)
        val newCategoryChooseIt = datastore.getCategoryById(id)

        if(newCategoryChooseIt !in datastore.getCategories()) return false

        if (userTransaction != null && newCategoryChooseIt != null) {
            userTransaction.category = newCategoryChooseIt
            return true
        }
        return false
    }
}