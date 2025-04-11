package org.qudus.squad.testCases

import org.qudus.squad.dataSource.FinanceTrackerDataSourceImpl
import org.qudus.squad.logic.models.Transaction
import org.qudus.squad.logic.models.Category
import org.qudus.squad.logic.statements.ShowMonthlyTransactions
import java.util.*

class TestMonthlyTransactions(financeTrackerImpl: FinanceTrackerDataSourceImpl) :
    ShowMonthlyTransactions(financeTrackerImpl) {

    fun listOfTransactionsByMonth(month: String, year: Int): List<Transaction> {
        return getTransactionsByMonth(month, year)
    }

}

fun testMonthlyTransactionsCases() {
    val financeTrackerDataSourceImpl = FinanceTrackerDataSourceImpl()
    val monthlySheet = TestMonthlyTransactions(financeTrackerDataSourceImpl)

    val dateJan2025 = Calendar.getInstance().apply { set(2025, Calendar.JANUARY, 1) }.timeInMillis
    val dateApr2026 = Calendar.getInstance().apply { set(2026, Calendar.APRIL, 1) }.timeInMillis


    // adding Categories
    val salary = Category("Salary", 1)
    val rent = Category("Rent", 2)
    val food = Category("Food", 3)
    val investment = Category("Investment", 4)
    val electricity = Category("Electricity", 5)

    financeTrackerDataSourceImpl.addTransaction(Transaction(1, "income", 1000.0, dateJan2025, salary))
    val result1 = monthlySheet.listOfTransactionsByMonth("JAN", 2025)
    test("Single income transaction", result1.size == 1 && result1[0].type.lowercase() == "income", true)


    financeTrackerDataSourceImpl.removeTransaction(1)
    financeTrackerDataSourceImpl.addTransaction(Transaction(2, "expense", 500.0, dateJan2025, rent))
    val result2 = monthlySheet.listOfTransactionsByMonth("JAN", 2025)
    test("Single expense transaction", result2.size == 1 && result2[0].type.lowercase() == "expense", true)


    financeTrackerDataSourceImpl.removeTransaction(2)
    financeTrackerDataSourceImpl.addTransaction(Transaction(3, "income", 1500.0, dateJan2025, salary))
    financeTrackerDataSourceImpl.addTransaction(Transaction(4, "expense", 200.0, dateJan2025, food))
    financeTrackerDataSourceImpl.addTransaction(Transaction(5, "expense", 300.0, dateJan2025, electricity))
    val result3 = monthlySheet.listOfTransactionsByMonth("JAN", 2025)
    test("Multiple transactions", result3.size == 3 && result3.count { it.type.lowercase() == "income" } == 1, true)



    financeTrackerDataSourceImpl.addTransaction(Transaction(6, "income", 900.0, dateApr2026, salary))
    val result4 = monthlySheet.listOfTransactionsByMonth("APR", 2026)
    test("Different year", result4.size == 1 && result4[0].timeStamp == dateApr2026, true)


    val result5 = monthlySheet.listOfTransactionsByMonth("XYZ", 2025)
    test("Invalid month input", result5.isEmpty(), true)

    val result6 = monthlySheet.listOfTransactionsByMonth("DEC", 2025)
    test("Empty month", result6.isEmpty(), true)


    financeTrackerDataSourceImpl.addTransaction(Transaction(7, "expense", 400.0, dateJan2025, rent))
    financeTrackerDataSourceImpl.addTransaction(Transaction(8, "income", 1200.0, dateJan2025, salary))
    val result7 = monthlySheet.listOfTransactionsByMonth("JAN", 2025)
    test("Transaction order", result7.first().type.lowercase() == "income", true)
}

fun test(name: String, result: Boolean, correctResult: Boolean) {
    if (result == correctResult) {
        println("✅ Success - $name")
    } else {
        println("❌ Failed - $name | Expected: $correctResult, Got: $result")
    }
}
