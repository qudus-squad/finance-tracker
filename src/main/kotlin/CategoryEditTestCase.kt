package org.qudus.squad

fun main(){
    categoryEditTestCase(
        testCase = "Give a Empty new category name, should return false",
        categoryPreviousName = "Groceries",
        categoryNewName = "",
        expectedResult = false
    )
    categoryEditTestCase(
        testCase = "Give a Previous category name does not exist, should return false",
        categoryPreviousName = "NonExistingCategory",
        categoryNewName = "NewCategory",
        expectedResult = false
    )
    categoryEditTestCase(
        testCase = "Give a New category name is empty, should return false",
        categoryPreviousName = "Groceries",
        categoryNewName = "",
        expectedResult = false
    )
    categoryEditTestCase(
        testCase = "Give a New name is same as old name, should return false",
        categoryPreviousName = "Bills",
        categoryNewName = "Bills",
        expectedResult = false
    )
    categoryEditTestCase(
        testCase = "Give a New category name already exists, should return false",
        categoryPreviousName = "Groceries",
        categoryNewName = "Utilities", // موجود مسبقاً
        expectedResult = false
    )
    categoryEditTestCase(
        testCase = "Give a Previous category name is empty, should return false",
        categoryPreviousName = "",
        categoryNewName = "NewCategory",
        expectedResult = false
    )
    categoryEditTestCase(
        testCase = "Give a New name contains invalid characters, should return false",
        categoryPreviousName = "Groceries",
        categoryNewName = "Food@123",
        expectedResult = false
    )
    categoryEditTestCase(
        testCase = "Give a New name contains only spaces, should return false",
        categoryPreviousName = "Groceries",
        categoryNewName = "   ",
        expectedResult = false
    )
}

fun categoryEditTestCase(testCase : String , categoryPreviousName : String , categoryNewName : String, expectedResult : Boolean){
    println(testCase + " | " +  (CategoryEditImplementation().categoryEdit(categoryPreviousName,categoryNewName) == expectedResult))
}