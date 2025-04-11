package org.qudus.squad.logic.categories

import org.qudus.squad.logic.FinanceTrackerDataSource
import org.qudus.squad.logic.models.Category
import org.qudus.squad.testCases.testDeleteCategoryFunction

class DeleteCategory(private val dataSource: FinanceTrackerDataSource) {

    fun removeCategory(category: Category) : Boolean {
        return dataSource.removeCategory(category.id)
    }
}

fun main() {
    testDeleteCategoryFunction()
}