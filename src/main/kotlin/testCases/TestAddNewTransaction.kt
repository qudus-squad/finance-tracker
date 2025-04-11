package org.qudus.squad.testCases

import org.qudus.squad.logic.models.Category
import org.qudus.squad.logic.models.Transaction
import org.qudus.squad.logic.models.TransactionType
import org.qudus.squad.logic.transactions.AddNewTransaction

class TestAddNewTransaction(
    private val addNewTransaction: AddNewTransaction
) {
    private val transaction = Transaction(
        type = TransactionType.Deposit,
        category = Category(1, "Rent"),
        amount = -1.0,
        timeStamp = 20250,
    )

    fun check() {
        test(
            title = "When Transaction Amount Is Less Than Zero , Should Return False",
            result = addNewTransaction.isAmountValid(transaction),
            expectedResult = false,
        )
    }

    fun test(title: String, result: Boolean, expectedResult: Boolean) {
        if (result == expectedResult) {
            println("Success - $title")
        } else {
            println("Failed - $title")
        }
    }
}