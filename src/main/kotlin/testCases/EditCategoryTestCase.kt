package org.qudus.squad.testCases

import org.qudus.squad.logic.categories.EditCategory
import org.qudus.squad.logic.models.Category

class EditCategoryTestCase(private val editCategory: EditCategory ){

    fun test() {
        categoryEditTestCase(
            testCase = "Give a Empty new category name, should return false",
            categoryName = "Groceries",
            newCategoryName = "",
            expectedResult = false
        )
        categoryEditTestCase(
            testCase = "Give a Previous category name does not exist, should return false",
            categoryName = "NonExistingCategory",
            newCategoryName = "NewCategory",
            expectedResult = false
        )
        categoryEditTestCase(
            testCase = "Give a New name is same as old name, should return false",
            categoryName = "Bills",
            newCategoryName = "Bills",
            expectedResult = false
        )
        categoryEditTestCase(
            testCase = "Give a New category name already exists, should return false",
            categoryName = "Groceries",
            newCategoryName = "Utilities",
            expectedResult = false
        )
        categoryEditTestCase(
            testCase = "Give a Previous category name is empty, should return false",
            categoryName = "",
            newCategoryName = "NewCategory",
            expectedResult = false
        )
        categoryEditTestCase(
            testCase = "Give a New name contains invalid characters, should return false",
            categoryName = "Groceries",
            newCategoryName = "Food@123",
            expectedResult = false
        )
        categoryEditTestCase(
            testCase = "Valid edit: change 'Groceries' to 'Food', should return true",
            categoryName = "Groceries",
            newCategoryName = "Food",
            expectedResult = true
        )
    }
    private fun categoryEditTestCase(
        testCase: String,
        categoryName: String,
        newCategoryName: String,
        expectedResult: Boolean
    ) {
        val category = Category(name = categoryName)
        val result = editCategory.editCategory(category.id, newCategoryName)
        println("$testCase | Expected: $expectedResult, Got: $result => ${if (result == expectedResult) "✅ PASS" else "❌ FAIL"}")
    }
}



