package org.qudus.squad.logic.statements

import kotlinx.datetime.*
import org.qudus.squad.dataSource.FinanceTrackerDataSourceImpl
import org.qudus.squad.logic.models.Transaction
import org.qudus.squad.logic.models.TransactionType
import org.qudus.squad.Utilities

class ShowMonthlyTransactions(private val financeTrackerImplementation: FinanceTrackerDataSourceImpl) {

    protected fun getTransactionsByMonth(month: String, year: Int): List<Transaction> {
        val monthEnum = Month.fromString(month) ?: return emptyList()

        return financeTrackerImplementation.getAllTransactions()
            .filter { transaction ->
                val calender = Instant.fromEpochMilliseconds(transaction.timestamp * 1000)
                    .toLocalDateTime(TimeZone.currentSystemDefault())

                val transactionMonth = calender.monthNumber
                val transactionYear = calender.year

                transactionMonth == monthEnum.value && transactionYear == year
            }
            .sortedBy { transaction ->
                if (transaction.type == TransactionType.Deposit) 0 else 1
            }
    }
    fun displayMonthlySheet(month: String, year: Int) {
        val transactions = getTransactionsByMonth(month, year)

        if (transactions.isEmpty()) {
            return
        }

        for (transaction in transactions) {
            val isIncome = transaction.type == TransactionType.Deposit
            val typeOfTransaction = if (isIncome) "Income" else "Expense"
            val sign = if (isIncome) "+" else "-"
            val formattedDate = Utilities.timestampToFormattedDate(transaction.timestamp)
        }
    }
}

enum class Month(val value: Int) {
    JAN(0), FEB(1), MAR(2), APR(3), MAY(4), JUN(5),
    JUL(6), AUG(7), SEP(8), OCT(9), NOV(10), DEC(11);

    companion object {
        fun fromString(name: String): Month? =
            entries.find { element -> element.name.equals(name, ignoreCase = true) }
    }
}
