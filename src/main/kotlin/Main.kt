package org.qudus.squad

import kotlinx.datetime.Month
import org.qudus.squad.dataSource.FinanceTrackerDataSourceImpl
import org.qudus.squad.logic.FinanceTrackerDataSource
import org.qudus.squad.logic.categories.AddNewCategory
import org.qudus.squad.logic.categories.DeleteCategory
import org.qudus.squad.logic.categories.EditCategory
import org.qudus.squad.logic.models.Category
import org.qudus.squad.logic.models.Transaction
import org.qudus.squad.logic.models.TransactionType
import org.qudus.squad.logic.statements.GetFinancialTrackerDetails
import org.qudus.squad.logic.transactions.AddNewTransaction
import org.qudus.squad.logic.transactions.DeleteTransaction
import org.qudus.squad.logic.transactions.EditTransaction
import java.text.SimpleDateFormat
import java.util.*

fun returnFinanceTrackerData(
    dataSource: FinanceTrackerDataSource
): FinanceTrackerDataSource {
    return dataSource
}

val dataSource = returnFinanceTrackerData(FinanceTrackerDataSourceImpl())
val addNewTransactionImpl = AddNewTransaction(dataSource)
val editTransactionImpl = EditTransaction(dataSource)
val deleteTransactionImpl = DeleteTransaction(dataSource)
val addNewCategoryImpl = AddNewCategory(dataSource)
val editCategoryImpl = EditCategory(dataSource)
val deleteCategoryImpl = DeleteCategory(dataSource)
val getFinancialDetailsImp = GetFinancialTrackerDetails(dataSource)

fun main() {
    while (true) {
        println("\n=== Main Menu ===")
        println("1. Manage Transaction")
        println("2. Manage Category")
        println("3. Monthly Sheet")
        println("4. Balance")
        println("0. Exit")
        print("Enter your choice: ")
        when (readlnOrNull()?.trim()) {
            "1" -> manageTransactionMenu()
            "2" -> manageCategoryMenu()
            "3" -> getMonthlyTransactions()
            "4" -> getBalance()
            "0" -> {
                println("Goodbye!")
                return
            }

            else -> println("Invalid choice. Please try again.")
        }
    }
}

//////////////////////////// MANAGE TRANSACTION MENU //////////////////////////( 0 -> 1 )

fun manageTransactionMenu() {
    while (true) {
        println("\n--- Transaction Menu ---")
        println("1. View Recent Transactions")
        println("2. Add Transaction")
        println("3. Edit Transaction")
        println("4. Delete Transaction")
        println("0. Back to Main Menu")
        print("Enter your choice: ")
        when (readlnOrNull()?.trim()) {
            "1" -> viewAllTransactions()
            "2" -> addNewTransaction()
            "3" -> editExistingTransaction()
            "4" -> deleteExistingTransaction()
            "0" -> return
            else -> println("Invalid choice. Please try again.")
        }
    }
}

//////////////////////////// VIEW RECENT TRANSACTION  //////////////////////////( 0 -> 1 -> 1 )

fun viewAllTransactions() {
    val transactions = dataSource.getAllTransactions()

    if (transactions.isEmpty()) {
        println("No transactions found.")
        return
    }
    println("\n--- Recent Transactions ---")
    displayTransactions(transactions)
    println("\nTotal Income: ${dataSource.getTotalIncome()}")
    println("Total Expenses: ${dataSource.getTotalExpenses()}")
}

fun displayTransactions(transactions: List<Transaction>) {
    val dateFormat = SimpleDateFormat("dd-MM-yyyy")
    transactions.forEach { transaction ->
        val type = if (transaction.type == TransactionType.Deposit) "INCOME" else "EXPENSE"
        val sign = if (transaction.type == TransactionType.Deposit) "+" else "-"
        val date = dateFormat.format(Date(transaction.timestamp))
        println("ID: ${transaction.id} | $type | $sign${transaction.amount} | Category: ${transaction.category.name} | Date: $date")
    }
}

fun displayTransactionById() {
    print("Enter transaction id to view: ")
    val id = readlnOrNull()?.toIntOrNull()
    if (id == null || id.toString().isEmpty()) {
        println("ID should not be empty!")
        return
    }
}

//////////////////////////// ADD NEW TRANSACTION MENU //////////////////////////( 0 -> 1 -> 2)

fun addNewTransaction() {
    val categories = dataSource.getCategories()
    if (categories.isEmpty()) {
        println("No categories available. Please add a category first.")
        return
    }
    ///TRANSACTION TYPE SELECTION
    println("Select transaction type:")
    println("1. Income (Deposit)")
    println("2. Expense (Withdraw)")
    print("Enter choice: ")
    val typeSelected = readlnOrNull()?.trim() ?: ""
    val transactionType = when (typeSelected) {
        "1" -> TransactionType.Deposit
        "2" -> TransactionType.Withdraw
        else -> {
            println("Invalid choice. Transaction canceled.")
            return
        }
    }

    ///TRANSACTION CATEGORY SELECTION
    println("\nAvailable Categories:")
    categories.forEachIndexed { index, category ->
        println("${index + 1}. ${category.name}")
    }
    print("Select Category: ")
    val categorySelected = readlnOrNull()?.trim()?.toIntOrNull()?.minus(1)

    if (categorySelected == null || categorySelected < 0 || categorySelected >= categories.size) {
        println("Invalid category selection. Transaction canceled.")
        return
    }

    ///TRANSACTION AMOUNT SELECTION
    print("Enter amount: ")
    val amountSelected = readlnOrNull()?.trim() ?: ""
    val amount = amountSelected.toDoubleOrNull()

    if (amount == null || amount <= 0) {
        println("Invalid amount. Amount must be a positive number.")
        return
    }

    ///TRANSACTION DATE SELECTION
    print("Enter date (dd-MM-yyyy) or leave empty for today's Date: ")
    val dateSelected = readlnOrNull()?.trim() ?: ""

    val timestamp = if (dateSelected.isEmpty()) {
        System.currentTimeMillis()
    } else {
        try {
            Utils.parseDateStringToTimestamp(dateSelected)
        } catch (e: Exception) {
            println("Invalid date format. Using current date.")
            System.currentTimeMillis()
        }
    }
    ///COMPLETE TRANSACTION
    val transaction = Transaction(
        type = transactionType,
        amount = amount,
        timestamp = timestamp,
        category = categories[categorySelected]
    )
    if (addNewTransactionImpl.addNewTransaction(transaction)) {
        println("Transaction added successfully with ID: ${transaction.id}")
    } else {
        println("Failed to add transaction.")
    }
}

//////////////////////////// EDIT EXISTING TRANSACTION  //////////////////////////( 0 -> 1 -> 3 )

fun editExistingTransaction() {
    print("Enter transaction ID to update: ")
    val id = readlnOrNull()?.toIntOrNull()

    if (id == null) {
        println("Invalid ID")
        return
    }

    val transaction = dataSource.getTransactionById(id)
    if (transaction == null) {
        println("Transaction with ID $id not found.")
        return
    }
    println("Current transaction details:")
    displayTransactions(listOf(transaction))

    //SELECT NEW TYPE
    println("\nSelect new transaction type:")
    println("1. Income (Deposit)")
    println("2. Expense (Withdraw)")
    println("3. Keep current (${transaction.type})")
    print("Enter choice: ")

    val typeChoice = readlnOrNull()?.trim() ?: "3"
    val newType = when (typeChoice) {
        "1" -> TransactionType.Deposit
        "2" -> TransactionType.Withdraw
        else -> transaction.type
    }

    //SELECT NEW AMOUNT
    print("Enter new amount (current: ${transaction.amount}) or leave empty to keep current: ")
    val amountSelected = readlnOrNull()?.trim() ?: ""
    val newAmount =
        if (amountSelected.isEmpty()) transaction.amount else amountSelected.toDoubleOrNull() ?: transaction.amount

    //SELECT NEW CATEGORY
    val categories = dataSource.getCategories()
    println("\nAvailable Categories:")
    categories.forEachIndexed { index, category ->
        println("${index + 1}. ${category.name} (ID: ${category.id})")
    }
    println("${categories.size + 1}. Keep current (${transaction.category.name})")

    print("Enter category number: ")
    val categoryIndexStr = readlnOrNull()?.trim() ?: ""
    val categoryIndex = categoryIndexStr.toIntOrNull()?.minus(1) ?: categories.size

    val newCategory =
        if (categoryIndex < 0 || categoryIndex >= categories.size) transaction.category else categories[categoryIndex]

    //SELECT NEW DATE
    print("Enter new date (dd-MM-yyyy) or leave empty to keep current: ")
    val dateSelected = readlnOrNull()?.trim() ?: ""

    val newTimestamp = if (dateSelected.isEmpty()) {
        transaction.timestamp
    } else {
        try {
            Utils.parseDateStringToTimestamp(dateSelected)
        } catch (e: Exception) {
            println("Invalid date format. Keeping current date.")
            transaction.timestamp
        }
    }

    // Use the EditTransaction class methods instead of direct data source
    val amountUpdated = editTransactionImpl.editTransactionAmount(transaction.id, newAmount)
    val typeUpdated = editTransactionImpl.editTransactionType(transaction.id, newType)
    val categoryUpdated = editTransactionImpl.editTransactionCategory(transaction.id, newCategory)

    // Only try to update timestamp if it was changed
    val timestampUpdated = if (newTimestamp != transaction.timestamp) {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy")
        val formattedDate = dateFormat.format(Date(newTimestamp))
        editTransactionImpl.editTransactionTimeStamp(transaction.id, formattedDate)
    } else {
        true // No change needed
    }

    if (amountUpdated && typeUpdated && categoryUpdated && timestampUpdated) {
        println("Transaction updated successfully.")
    } else {
        println("Some fields failed to update.")
    }
}


//////////////////////////// DELETE EXISTING TRANSACTION  //////////////////////////( 0 -> 1 -> 4 )

fun deleteExistingTransaction() {
    print("Enter transaction ID to delete: ")
    val id = readlnOrNull()?.toIntOrNull()
    if (id == null) {
        println("Invalid ID")
        return
    }
    if (deleteTransactionImpl.deleteTransaction(id)) {
        println("Transaction with ID $id deleted successfully.")
    } else {
        println("Failed to delete transaction. ID may not exist.")
    }
}

/**/////////////////////////// MANAGE CATEGORIES MENU //////////////////////////**( 0 -> 2 )

fun manageCategoryMenu() {
    while (true) {
        println("\n--- Category Menu ---")
        println("1. View Categories")
        println("2. Add Category")
        println("3. Edit Category")
        println("4. Delete Category")
        println("5. View Category by ID")
        println("0. Back to Main Menu")
        print("Enter your choice: ")
        when (readlnOrNull()?.trim()) {

            ///VIEW ALL CATEGORIES
            "1" -> displayCategories()

            ///ADD NEW CATEGORY
            "2" -> {
                print("Enter category name: ")
                val selectedName = readlnOrNull()?.trim() ?: ""
                if (selectedName.isEmpty()) {
                    println("Category name cannot be empty.")
                } else {
                    val category = Category(name = selectedName)
                    if (addNewCategoryImpl.addNewCategory(category)) {
                        println("Category added successfully with ID: ${category.id}")
                    } else {
                        println("Failed to add category.")
                    }
                }
            }

            ///EDIT CATEGORY
            "3" -> {
                print("Enter category id to update: ")
                val id = readlnOrNull()?.toIntOrNull()
                if (id == null) {
                    println("Invalid ID")
                    continue
                }
                val existingCategory = dataSource.getCategoryById(id)
                if (existingCategory == null) {
                    println("Category with ID $id not found.")
                    continue
                }
                print("Enter new category name: ")
                val name = readlnOrNull()?.trim() ?: ""
                if (name.isEmpty()) {
                    println("Category name cannot be empty.")
                    continue
                }
                if (editCategoryImpl.editCategory(id, name)) {
                    println("Category updated successfully.")
                } else {
                    println("Failed to update category.")
                }
            }
            ///DELETE EXISTING CATEGORY
            "4" -> {
                displayCategories()
                print("Enter category id to delete: ")
                val id = readlnOrNull()?.toIntOrNull()
                if (id == null) {
                    println("Invalid ID")
                    continue
                }
                val transactions = dataSource.getTransactionsByCategory(id)
                if (transactions.isNotEmpty()) {
                    println("Cannot delete category with ID $id - it has ${transactions.size} associated transactions.")
                    continue
                }
                val categoryToDelete = dataSource.getCategoryById(id)
                if (categoryToDelete != null) {
                    if (deleteCategoryImpl.removeCategory(categoryToDelete)) {
                        println("Category deleted successfully.")
                    } else {
                        println("Failed to delete category. ID may not exist.")
                    }
                } else {
                    println("Category not found with ID: $id")
                }
            }

            "0" -> return
            else -> println("Invalid choice. Please try again.")
        }
    }
}

fun displayCategories() {
    val categories = dataSource.getCategories()
    println("\nCategories:")
    if (categories.isEmpty()) {
        println("No categories found.")
    } else {
        categories.forEach { category ->
            println("ID: ${category.id} | Name: ${category.name}")
        }
    }
}

/**/////////////////////////// MONTHLY SHEET MENU //////////////////////////**( 0 -> 3 )

fun getMonthlyTransactions() {
    print("Enter month (e.g., JAN, FEB, MAR): ")
    val month = readlnOrNull()?.trim()?.uppercase() ?: ""

    print("Enter year (e.g., 2023): ")
    val year = readlnOrNull()?.trim()?.toIntOrNull() ?: Calendar.getInstance().get(Calendar.YEAR)

    val monthEnum = Month.entries.find { it.name == month }
    if (monthEnum == null) {
        println("Invalid month. Please enter a valid three-letter month abbreviation (e.g., JAN, FEB).")
        return
    }

    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.MONTH, monthEnum.value)
    calendar.set(Calendar.DAY_OF_MONTH, 1)
    val startTime = calendar.timeInMillis

    calendar.add(Calendar.MONTH, 1)
    calendar.add(Calendar.MILLISECOND, -1)
    val endTime = calendar.timeInMillis

    val transactions = dataSource.getTransactionsInTimeRange(startTime, endTime)

    println("\n=== Monthly Summary for $month $year ===")
    if (transactions.isEmpty()) {
        println("No transactions found for this period.")
        return
    }

    val income = transactions.filter { it.type == TransactionType.Deposit }.sumOf { it.amount }
    val expenses = transactions.filter { it.type == TransactionType.Withdraw }.sumOf { it.amount }
    val balance = income - expenses

    displayTransactions(transactions)

    println("\nSummary:")
    println("Total Income: $income")
    println("Total Expenses: $expenses")
    println("Net Balance: $balance")
}

/**/////////////////////////// VIEW CURRENT BALANCE MENU //////////////////////////**( 0 -> 4 )

fun getBalance() {
    val balance = getFinancialDetailsImp.getBalance()
    println("Your Balance Is : $balance")
}