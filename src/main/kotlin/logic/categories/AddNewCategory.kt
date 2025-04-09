package org.qudus.squad.logic.categories

import org.qudus.squad.logic.FinanceTrackerDataSource
import org.qudus.squad.logic.models.Category

class AddNewCategory(
    private val dataSource:FinanceTrackerDataSource
) {

    fun addNewCategory(category: Category){
        dataSource.addCategory(category)
    }
}