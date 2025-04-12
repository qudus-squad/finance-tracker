package org.qudus.squad.testCases

import org.qudus.squad.dataSource.FinanceTrackerDataSourceImpl
import org.qudus.squad.logic.FinanceTrackerDataSource
import org.qudus.squad.logic.models.Transaction
import org.qudus.squad.logic.models.Category
import org.qudus.squad.logic.models.TransactionType
import org.qudus.squad.logic.statements.ShowMonthlyTransactions
import java.util.*


fun testMonthlyTransactionsCases() {
    val financeTrackerDataSource: FinanceTrackerDataSource = FinanceTrackerDataSourceImpl()
    val monthlySheet = ShowMonthlyTransactions(financeTrackerDataSource)

    val dateJan2025 = Calendar.getInstance().apply { set(2025, Calendar.JANUARY, 1) }.timeInMillis
    val dateApr2026 = Calendar.getInstance().apply { set(2026, Calendar.APRIL, 1) }.timeInMillis


    // adding Categories
    val salary = Category(1, "Salary")
    val rent = Category(2, "Rent")
    val food = Category(3, "Food")
    val investment = Category(4, "Investment")
    val electricity = Category(5, "Electricity")

    financeTrackerDataSource.addNewTransaction(Transaction(1, TransactionType.Deposit, 1000.0, dateJan2025, salary))
    val result1 = monthlySheet.displayMonthlySheet(monthlySheet.getDaysInMonthInMillis(2025 , 1), 2025)
    test("Single income transaction", result1.size == 1 && result1[0].type == TransactionType.Deposit, true)


    financeTrackerDataSource.removeTransaction(1)
    financeTrackerDataSource.addNewTransaction(Transaction(2, TransactionType.Withdraw, 500.0, dateJan2025, rent))
    val result2 =monthlySheet.displayMonthlySheet(monthlySheet.getDaysInMonthInMillis(2025 , 1), 2025)
    test("Single expense transaction", result2.size == 1 && result2[0].type == TransactionType.Withdraw, true)


    financeTrackerDataSource.removeTransaction(2)
    financeTrackerDataSource.addNewTransaction(Transaction(3, TransactionType.Deposit, 1500.0, dateJan2025, salary))
    financeTrackerDataSource.addNewTransaction(Transaction(4, TransactionType.Withdraw, 200.0, dateJan2025, food))
    financeTrackerDataSource.addNewTransaction(
        Transaction(
            5,
            TransactionType.Withdraw,
            300.0,
            dateJan2025,
            electricity
        )
    )
    val result3 =monthlySheet.displayMonthlySheet(monthlySheet.getDaysInMonthInMillis(2025 , 1), 2025)
    test("Multiple transactions", result3.size == 3 && result3.count { it.type == TransactionType.Deposit } == 1, true)



    financeTrackerDataSource.addNewTransaction(Transaction(6, TransactionType.Deposit, 900.0, dateApr2026, salary))
    val result4 = monthlySheet.displayMonthlySheet(monthlySheet.getDaysInMonthInMillis(2025 , 4), 2026)
    test("Different year", result4.size == 1 && result4[0].timestamp == dateApr2026, true)


    val result5 =monthlySheet.displayMonthlySheet(monthlySheet.getDaysInMonthInMillis(2025 , 13), 2025)
    test("Invalid month input", result5.isEmpty(), true)


    financeTrackerDataSource.addNewTransaction(Transaction(7, TransactionType.Withdraw, 400.0, dateJan2025, rent))
    financeTrackerDataSource.addNewTransaction(Transaction(8, TransactionType.Deposit, 1200.0, dateJan2025, salary))
    val result7 = monthlySheet.displayMonthlySheet(monthlySheet.getDaysInMonthInMillis(2025 , 13), 2025)
    test("Transaction order", result7.first().type == TransactionType.Deposit, true)
}

fun test(name: String, result: Boolean, correctResult: Boolean) {
    if (result == correctResult) {
        println("✅ Success - $name")
    } else {
        println("❌ Failed - $name | Expected: $correctResult, Got: $result")
    }
}
