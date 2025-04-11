package org.qudus.squad


import org.qudus.squad.testCases.testDeleteCategoryFunction
import org.qudus.squad.testCases.testMonthlyTransactionsCases

fun main() {

    while (true) {
        println("\n=== Main Menu ===")
        println("1. Transaction CRUD")
        println("2. Category CRUD")
        println("3. Monthly Summary")
        println("0. Exit")
        print("Enter your choice: ")
        when (readlnOrNull()?.trim()) {
            "1" -> transactionMenu()
            "2" -> categoryMenu()
            "3" -> getMonthlySummary()
            "0" -> {
                println("Goodbye!")
                return
            }

            else -> println("Invalid choice. Please try again.")
        }
    }

}

fun transactionMenu() {
    while (true) {
        println("\n--- Transaction Menu ---")
        println("1. Add Transaction")
        println("2. View Transactions")
        println("3. Update Transaction")
        println("4. Delete Transaction")
        println("5. View Transaction by ID")
        println("0. Back to Main Menu")
        print("Enter your choice: ")
        when (readlnOrNull()?.trim()) {
            "1" -> {}

            "2" -> {}

            "3" -> {}

            "4" -> {}

            "5" -> displayTransactionById()

            "0" -> return

            else -> println("Invalid choice. Please try again.")
        }
    }
}

fun categoryMenu() {
    while (true) {
        println("\n--- Category Menu ---")
        println("1. Add Category")
        println("2. View Categories")
        println("3. Update Category")
        println("4. Delete Category")
        println("5. View Category by ID")
        println("0. Back to Main Menu")
        print("Enter your choice: ")
        when (readlnOrNull()?.trim()) {
            "1" -> {
                print("Enter category name: ")
                val name = readlnOrNull() ?: ""
            }

            "2" -> {
                println("\nCategories:")
            }

            "3" -> {
                print("Enter category id to update: ")
                val id = readlnOrNull()?.toIntOrNull() ?: 0
                print("Enter new category name: ")
                val name = readlnOrNull() ?: ""
            }

            "4" -> {
                print("Enter category id to delete: ")
                val id = readlnOrNull()?.toIntOrNull() ?: 0

            }

            "5" -> displayCategoryById()

            "0" -> return
            else -> println("Invalid choice. Please try again.")
        }
    }
}

fun getMonthlySummary() {
    // TODO()
}

fun displayTransactionById() {
    print("Enter transaction id to view: ")
    val id = readlnOrNull()?.toIntOrNull()
    if(id == null || id.toString().isEmpty()){
        println("ID should not be empty!")
        return
    }
}

fun displayCategoryById() {
    print("Enter category id to view: ")
    val id = readlnOrNull()?.toIntOrNull()
    if(id == null || id.toString().isEmpty()){
        println("ID should not be empty!")
        return
    }
}