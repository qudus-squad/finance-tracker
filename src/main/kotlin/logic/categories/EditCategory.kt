package org.qudus.squad.logic.categories

import org.qudus.squad.dataSource.FinanceTrackerDataSourceImpl
import org.qudus.squad.logic.models.Category

class EditCategory(private val dataSource:FinanceTrackerDataSourceImpl) {

    fun editCategory(newCategory: Category, oldCategory: String) : Boolean{
        return dataSource.updateCategory(newCategory)
    }
}