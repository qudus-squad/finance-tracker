package org.qudus.squad.logic.categories

import org.qudus.squad.logic.FinanceTrackerDataSource

class EditCategory(private val dataSource: FinanceTrackerDataSource) {

    fun editCategory(categoryId: Int, newCategoryName: String): Boolean {
        val category = dataSource.getCategoryById(categoryId) ?: return false
        return dataSource.editExistingCategory(category.copy(name = newCategoryName))
    }
}