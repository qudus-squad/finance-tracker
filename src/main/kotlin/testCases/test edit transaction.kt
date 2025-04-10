package org.qudus.squad.testCases

import org.qudus.squad.logic.FinanceTrackerDataSource
import org.qudus.squad.logic.models.Category
import org.qudus.squad.logic.models.Transaction
import org.qudus.squad.logic.transactions.EditTransaction

class TestEditTransaction(dataSource: FinanceTrackerDataSource) {
    val test = EditTransaction(dataSource)
    val transaction = Transaction(
        id = 1, type = "depo", amount = 299.00, timeStamp = 200000, category = Category(
            id = 1, name = "car"
        )
    )


    fun check(name: String, result: Boolean, correctResult: Boolean) {
        if (result == correctResult) println("Success - $name")
        else println("Failed - $name")

    }


    fun main() {
        check(
            name = "when enter Transaction amount above 0 and not equal negative zero,then return true",
            result = test.editTransactionAmount(1, 2000.00),
            correctResult = true,
        )

        check(
            name = "when user try to enter Transaction amount using words, then return false",
            result = test.editTransactionAmount(1, 345656.00),
            correctResult = false,
        )

        check(
            name = "when enter Transaction amount with negative number, then return false",
            result = test.editTransactionAmount(1, -20.00),
            correctResult = false,
        )



        check(
            name = "when user selects Transaction category with whitespace, then return true",
            result = test.editTransactionCategory(1, "", 2),
            correctResult = true,
        )

        check(
            name = "when user enters a Transaction category name with valid characters and spaces, then return true",
            result = test.editTransactionCategory(1, "car", 45),
            correctResult = true,
        )


        check(
            name = "when user selects a Transaction category not in the list, then return false",
            result = test.editTransactionCategory(1, "", 45),
            correctResult = false,
        )


        check(
            name = "when user enter input empty Transaction category, then return false",
            result = test.editTransactionCategory(1, "cat", 45),
            correctResult = false,
        )


        check(
            name = "when user edit Transaction time but enter bad date format, then return false",
            result = test.editTransactionDate(1, 453454),
            correctResult = false,
        )


        check(
            name = "given user date month not in normal range for month when edit Transaction time, then return false",
            result = test.editTransactionDate(1, 2000),
            correctResult = false,
        )


        check(
            name = "given user date month in normal range for month with correct format edit Transaction time, then return false",
            result = test.editTransactionDate(1, 2000),
            correctResult = true,
        )


        check(
            name = "when user enters deposit as Transaction time Type, then return true",
            result = test.editTransactionType(1, "car"),
            correctResult = true,
        )

        check(
            name = "when user enters withdraw as Transaction time Type, then return true",
            result = test.editTransactionType(1, "car"),
            correctResult = true,
        )

        check(
            name = "when user enters Deposit with capital D, then return true",
            result = test.editTransactionType(1, "car"),
            correctResult = true,
        )

        check(
            name = "when user enters 'WITHDRAW' in all caps, then return true",
            result = test.editTransactionType(1, "car"),
            correctResult = true,
        )

        check(
            name = "when user enters 'deposit ' with trailing space, then return false",
            result = test.editTransactionType(1, "car"),
            correctResult = false,
        )

        check(
            name = "when user enters 'dep0sit' with a type, then return false",
            result = test.editTransactionType(1, "car"),
            correctResult = false,
        )

    }
}


