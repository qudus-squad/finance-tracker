package org.qudus.squad.testCases

import org.qudus.squad.dataSource.FinanceTrackerDataSourceImpl
import org.qudus.squad.logic.categories.DeleteCategory
import org.qudus.squad.logic.models.Category

fun testDeleteCategoryFunction() {
    val dataSource = FinanceTrackerDataSourceImpl()
    val deleteCategory = DeleteCategory(dataSource)

    val shopping = Category(1, "Shopping")
    val travel = Category(2,"Travel")

    dataSource.addCategory(shopping)
    dataSource.addCategory(travel)

    val result1 = deleteCategory.removeCategory(shopping)
    test("Remove existing category", result1, true)

    val result2 = deleteCategory.removeCategory(shopping)
    test("Remove non-existing category (already removed)", result2, false)

    val result3 = deleteCategory.removeCategory(travel)
    test("Remove second existing category", result3, true)

    val remainingCategories = dataSource.getCategories()
    test("All categories removed", remainingCategories.isEmpty(), true)
}