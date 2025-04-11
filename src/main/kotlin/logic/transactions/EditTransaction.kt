package org.qudus.squad.logic.transactions

import org.qudus.squad.logic.FinanceTrackerDataSource

class EditTransaction(
   private val datastore: FinanceTrackerDataSource
) {
    fun editTransactionAmount(transactionId: Int, transactionAmount: Double) {
        if (isValidAmount(transactionAmount)) {
            val editedTransaction = datastore.getTransactionById(transactionId)

            if (editedTransaction != null) {
                editedTransaction.amount = transactionAmount
                datastore.updateTransaction(editedTransaction)
            }
        }
    }

    fun editTransactionDate(transactionId: Int, transactionDate: Long) {
        val editedTransaction = datastore.getTransactionById(transactionId)
        if (editedTransaction != null) {
            editedTransaction.timeStamp = transactionDate
            datastore.updateTransaction(editedTransaction)

        }
    }

    fun editTransactionCategory(transactionId: Int, transactionCategoryName: String, id: Int) {
        val editedTransaction = datastore.getTransactionById(transactionId)
        val newCategoryChooseIt = datastore.getCategoryById(id)

        if (newCategoryChooseIt !in datastore.getCategories())

            if (editedTransaction != null && newCategoryChooseIt != null) {
                editedTransaction.category = newCategoryChooseIt

            }
    }

    fun editTransactionType(transactionId: Int, transactionType: String) {
        val editedTransaction = datastore.getTransactionById(transactionId)

        if (editedTransaction != null) {
            editedTransaction.type = transactionType
        }
    }


    private fun isValidAmount(transactionAmount: Double): Boolean {
        if (transactionAmount < 0) return false
        return true
    }
}