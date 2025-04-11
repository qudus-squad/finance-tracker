package org.qudus.squad.testCases

import org.qudus.squad.dataSource.FinanceTrackerDataSourceImpl
import org.qudus.squad.logic.categories.EditCategory
import org.qudus.squad.logic.models.Category

class EditCategoryTestCase(private val editCategory: EditCategory = EditCategory(FinanceTrackerDataSourceImpl)){

    fun test() {
        categoryEditTestCase(
            testCase = "Give a Empty new category name, should return false",
            categoryPreviousName = "Groceries",
            categoryNewName = Category(name = ""),
            expectedResult = false
        )
        categoryEditTestCase(
            testCase = "Give a Previous category name does not exist, should return false",
            categoryPreviousName = "NonExistingCategory",
            categoryNewName = Category(name = "NewCategory"),
            expectedResult = false
        )
        categoryEditTestCase(
            testCase = "Give a New name is same as old name, should return false",
            categoryPreviousName = "Bills",
            categoryNewName = Category(name = "Bills"),
            expectedResult = false
        )
        categoryEditTestCase(
            testCase = "Give a New category name already exists, should return false",
            categoryPreviousName = "Groceries",
            categoryNewName = Category(name = "Utilities"),
            expectedResult = false
        )
        categoryEditTestCase(
            testCase = "Give a Previous category name is empty, should return false",
            categoryPreviousName = "",
            categoryNewName = Category(name = "NewCategory"),
            expectedResult = false
        )
        categoryEditTestCase(
            testCase = "Give a New name contains invalid characters, should return false",
            categoryPreviousName = "Groceries",
            categoryNewName = Category(name = "Food@123"),
            expectedResult = false
        )
        categoryEditTestCase(
            testCase = "Valid edit: change 'Groceries' to 'Food', should return true",
            categoryPreviousName = "Groceries",
            categoryNewName = Category(name = "Food"),
            expectedResult = true
        )
    }
    fun categoryEditTestCase(
        testCase: String,
        categoryPreviousName: String,
        categoryNewName: Category,
        expectedResult: Boolean
    ) {
        val result = editCategory.editCategory(categoryNewName, categoryPreviousName)
        println("$testCase | Expected: $expectedResult, Got: $result => ${if (result == expectedResult) "✅ PASS" else "❌ FAIL"}")
    }
}


