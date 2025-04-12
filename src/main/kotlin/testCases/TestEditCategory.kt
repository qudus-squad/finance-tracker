package org.qudus.squad.testCases
import org.qudus.squad.dataSource.FinanceTrackerDataSourceImpl
import org.qudus.squad.logic.models.Category
import org.qudus.squad.logic.models.TransactionType
import org.qudus.squad.logic.transactions.EditTransaction




fun checkCategory (name: String, result: Boolean, correctResult: Boolean) {
    if (result == correctResult) println("Success - $name")
    else println("Failed - $name")
}


    fun main() {
        val dataSource = FinanceTrackerDataSourceImpl()
        val test = EditTransaction(dataSource)


        checkCategory(
            name = "when enter Transaction amount above 0 and not equal negative zero, then return true",
            result = test.editTransactionAmount(1, 2000.00),
            correctResult = true,
        )

        checkCategory(
            name = "when user try to enter Transaction amount using words, then return false",
            result = test.editTransactionAmount(1, 345656.00),
            correctResult = false,
        )

        checkCategory(
            name = "when enter Transaction amount with negative number, then return false",
            result = test.editTransactionAmount(1, -20.00),
            correctResult = false,
        )

        checkCategory(
            name = "when user selects Transaction category with whitespace, then return true",
            result = test.editTransactionCategory(1, category = Category(1, name = "car")),
            correctResult = true,
        )

        checkCategory(
            name = "when user enters a Transaction category name with valid characters and spaces, then return true",
            result = test.editTransactionCategory(1, category = Category(45, name = "car")),
            correctResult = true,
        )

        checkCategory(
            name = "when user selects a Transaction category not in the list, then return false",
            result = test.editTransactionCategory(1, category = Category(999, name = "")),
            correctResult = false,
        )

        checkCategory(
            name = "when user enter input empty Transaction category, then return false",
            result = test.editTransactionCategory(1, category = Category(45, name = "")),
            correctResult = false,
        )

        checkCategory(
            name = "when user edit Transaction time but enter bad date format, then return false",
            result = test.editTransactionTimeStamp(1, "32-13-2025"),
            correctResult = false,
        )

        checkCategory(
            name = "given user date month not in normal range for month when edit Transaction time, then return false",
            result = test.editTransactionTimeStamp(1, "32-13-2025"),
            correctResult = false,
        )

        checkCategory(
            name = "given user date month in normal range for month with correct format edit Transaction time, then return true",
            result = test.editTransactionTimeStamp(1, "25-12-2025"),
            correctResult = true,
        )

        checkCategory(
            name = "when user enters deposit as Transaction time Type, then return true",
            result = test.editTransactionType(1, TransactionType.Deposit),
            correctResult = true,
        )

        checkCategory(
            name = "when user enters withdraw as Transaction time Type, then return true",
            result = test.editTransactionType(1, TransactionType.Withdraw),
            correctResult = true,
        )

        checkCategory(
            name = "when user enters Deposit with capital D, then return true",
            result = test.editTransactionType(1, TransactionType.Deposit),
            correctResult = true,
        )

        checkCategory(
            name = "when user enters 'WITHDRAW' in all caps, then return true",
            result = test.editTransactionType(1, TransactionType.Withdraw),
            correctResult = true,
        )

        checkCategory(
            name = "when user enters 'deposit ' with trailing space, then return false",
            result = test.editTransactionType(1, TransactionType.Deposit),
            correctResult = false,
        )

        checkCategory(
            name = "when user enters 'dep0sit' with a type, then return false",
            result = test.editTransactionType(1, TransactionType.Deposit),
            correctResult = false,
        )
    }
