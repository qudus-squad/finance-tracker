package org.qudus.squad.logic.statements

class ShowMonthlyTransactions {
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
