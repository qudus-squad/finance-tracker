package org.qudus.squad.logic.categories

import org.qudus.squad.logic.FinanceTrackerDataSource
import org.qudus.squad.logic.models.Category

class DeleteCategory(private val dataSource: FinanceTrackerDataSource) {

    fun removeCategory(category: Category) : Boolean {
        return dataSource.removeCategory(category.id)
    }
}