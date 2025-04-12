package org.qudus.squad.logic.statements
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import org.qudus.squad.Utilities
import org.qudus.squad.logic.FinanceTrackerDataSource
import org.qudus.squad.logic.models.Transaction

class ShowMonthlyTransactions(private val dataSource: FinanceTrackerDataSource) {

    fun getDaysInMonthInMillis(year: Int, month: Int): List<Long> {
        val months = Month.entries[month - 1]
        val isLeapYear = isLeapYear(year)
        val daysInMonth = months.length(isLeapYear)

        val currentDate = Utilities.getCurrentDate()

        return (1..daysInMonth).mapNotNull { day ->
            val dateInMillis = LocalDate(year, months, day)
                .atStartOfDayIn(TimeZone.currentSystemDefault())
                .toEpochMilliseconds()

            if (dateInMillis <= currentDate) dateInMillis else null
        }
    }

    private fun isLeapYear(year: Int): Boolean {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }


    fun displayMonthlySheet(months: List<Long>, year: Int): List<Transaction> {
        val transactions = mutableListOf<Transaction>()

        for (startOfDay in months) {
            val endOfDay = startOfDay + 86_400_000L
            val dailyTransactions = dataSource.getTransactionsInTimeRange(startOfDay, endOfDay)
            transactions.addAll(dailyTransactions)
        }
        return transactions
    }
}

fun isValidMonth(date: Int): Boolean {
    if (date in 1..12) return true
    return false
}
