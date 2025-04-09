package org.qudus.squad.logic.statements

import java.text.SimpleDateFormat

class ShowMonthlyTransactions(private val transactionObject: Transaction) {

    private fun getTransactionsByMonth(month: String, year: Int): List<Transaction> {
        val monthEnum = Month.fromString(month) ?: return emptyList()

        return transactionObject.getAllTransactions()
            .filter { element ->
                (element.date.month == monthEnum.value) && (element.date.year + 1900 == year)
            }
            .sortedBy { element ->
                if (element.isIncome) 0 else 1
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
            val typeOfTransaction = if (transaction.isIncome) "Income" else "Expense"
            val formattedDateForTransaction = dateFormat.format(transaction.date)
            println("$typeOfTransaction: ${transaction.amount} USD   -  ${transaction.category}  -  $formattedDateForTransaction")
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
