package org.qudus.squad

import kotlinx.datetime.*

object Utilities {
    fun timestampToFormattedDate(timestamp: Long): String {
        val dateTime = Instant.fromEpochMilliseconds(timestamp * 1000).toLocalDateTime(TimeZone.currentSystemDefault())
        return "%02d-%02d-%d".format(dateTime.dayOfMonth, dateTime.monthNumber, dateTime.year)
    }

    fun parseDateStringToTimestamp(dateString: String): Long {
        val parts = dateString.split("-")
        require(parts.size == 3) { "Date must be in dd-MM-yyyy format" }

        val day = parts[0].toInt()
        val month = parts[1].toInt()
        val year = parts[2].toInt()

        val date = LocalDate(year, month, day)
        val dateTime = date.atStartOfDayIn(TimeZone.currentSystemDefault())
        return dateTime.toEpochMilliseconds()
    }

}