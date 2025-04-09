package org.qudus.squad.logic.statements

import org.qudus.squad.logic.FinanceTrackerDataSource
import org.qudus.squad.logic.models.Transaction
import java.text.SimpleDateFormat
import java.util.*

class ShowMonthlyTransactions(private val dataSource: FinanceTrackerDataSource) {

    private fun getTransactionsByMonth(month: String, year: Int): List<Transaction> {
        val monthEnum = Month.fromString(month) ?: return emptyList()

        return dataSource.getAllTransactions()
            .filter { element ->
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = element.timeStamp

                val transactionMonth = calendar.get(Calendar.MONTH)      // 0-11
                val transactionYear = calendar.get(Calendar.YEAR)        // full year like 2025

                transactionMonth == monthEnum.value && transactionYear == year
            }
            .sortedBy { element ->
                if (element.amount > 0) 0 else 1
            }
    }

    fun displayMonthlySheet(month: String, year: Int) {
        val transactions = getTransactionsByMonth(month, year)

        if (transactions.isEmpty()) {
            println("No transactions found for $month $year.")
            return
        }

        println("Your ${month.uppercase()} Monthly Sheet Is:")

        val dateFormat = SimpleDateFormat("dd-MMM-yyyy")

        for (transaction in transactions) {
            val isIncome = transaction.type.lowercase() == "income"
            val typeOfTransaction = if (isIncome) "Income" else "Expense"
            val sign = if (isIncome) "+" else "-"
            val formattedDate = dateFormat.format(Date(transaction.timeStamp))

            println("$typeOfTransaction: $sign${transaction.amount} USD - ${transaction.category.name} - $formattedDate")
        }

        println("Choose 0 to continue")

    }
}

enum class Month(val value: Int) {
    JAN(0), FEB(1), MAR(2), APR(3), MAY(4), JUN(5),
    JUL(6), AUG(7), SEP(8), OCT(9), NOV(10), DEC(11);

    companion object {
        fun fromString(name: String): Month? = entries.find { element ->
            element.name.equals(name, ignoreCase = true)
        }
    }
}
