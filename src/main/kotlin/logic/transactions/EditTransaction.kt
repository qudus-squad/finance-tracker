package org.qudus.squad.logic.transactions

import org.qudus.squad.logic.FinanceTrackerDataSource
import org.qudus.squad.logic.models.TransactionType

class EditTransaction(
    val datastore: FinanceTrackerDataSource
) {
    fun editTransactionAmount(transactionId: Int, transactionAmount: Double): Boolean {
        if (isValidAmount(transactionAmount)) {
            val Editedtransaction = datastore.getTransactionById(transactionId)
            if (Editedtransaction != null) {
                Editedtransaction.amount = transactionAmount
                datastore.updateTransaction(Editedtransaction)
                return true
            }
        }
        return false
    }

    fun editTransactionDate(transactionId: Int, transactionDate: Long): Boolean {
        val Editedtransaction = datastore.getTransactionById(transactionId)
        if (Editedtransaction != null) {
            Editedtransaction.timeStamp = transactionDate
            datastore.updateTransaction(Editedtransaction)

            return true
        }
        return false
    }


    fun editTransactionCategory(transactionId: Int, transactionCategoryName: String, id: Int): Boolean {
        val Editedtransaction = datastore.getTransactionById(transactionId)
        val newCategoryChooseIt = datastore.getCategoryById(id)

        if (newCategoryChooseIt !in datastore.getCategories()) return false

        if (Editedtransaction != null && newCategoryChooseIt != null) {
            Editedtransaction.category = newCategoryChooseIt
            return true
        }
        return false
    }

    fun editTransactionType(transactionId: Int, transactionType: TransactionType): Boolean {
        val Editedtransaction = datastore.getTransactionById(transactionId)

        if (Editedtransaction != null) {
            Editedtransaction.type = transactionType
            return true
        }
        return false
    }


    fun isValidAmount(transactionAmount: Double): Boolean {
        if (transactionAmount < 0) return false
        return true
    }
}