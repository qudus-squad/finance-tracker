package org.qudus.squad.dataSource

import org.qudus.squad.logic.models.*
import java.io.File

object FileDatabase {
    private const val CATEGORIES_FILE = "categories.txt"
    private const val TRANSACTIONS_FILE = "transactions.txt"
    private const val COUNTERS_FILE = "counters.txt"

    fun loadCategories(): List<Category> {
        val file = File(CATEGORIES_FILE)
        if (!file.exists()) return emptyList()

        return try {
            file.readLines().mapNotNull { line ->
                val parts = line.split("|")
                if (parts.size >= 2) {
                    val id = parts[0].toIntOrNull() ?: return@mapNotNull null
                    val name = parts[1]
                    Category(id = id, name = name)
                } else null
            }
        } catch (e: Exception) {
            println("Error loading categories: ${e.message}")
            emptyList()
        }
    }

    fun saveCategories(categories: List<Category>) {
        val file = File(CATEGORIES_FILE)
        try {
            file.bufferedWriter().use { writer ->
                categories.forEach { category ->
                    writer.write("${category.id}|${category.name}")
                    writer.newLine()
                }
            }
        } catch (e: Exception) {
            println("Error saving categories: ${e.message}")
        }
    }

    fun loadTransactions(categories: List<Category>): List<Transaction> {
        val file = File(TRANSACTIONS_FILE)
        if (!file.exists()) return emptyList()

        return try {
            file.readLines().mapNotNull { line ->
                val parts = line.split("|")
                if (parts.size >= 5) {
                    val id = parts[0].toIntOrNull() ?: return@mapNotNull null
                    val typeStr = parts[1]
                    val type = when (typeStr) {
                        "Deposit" -> TransactionType.Deposit
                        "Withdraw" -> TransactionType.Withdraw
                        else -> return@mapNotNull null
                    }
                    val amount = parts[2].toDoubleOrNull() ?: return@mapNotNull null
                    val timestamp = parts[3].toLongOrNull() ?: return@mapNotNull null
                    val categoryId = parts[4].toIntOrNull() ?: return@mapNotNull null

                    val category = categories.find { it.id == categoryId }
                        ?: return@mapNotNull null

                    Transaction(
                        id = id,
                        type = type,
                        amount = amount,
                        timestamp = timestamp,
                        category = category
                    )
                } else null
            }
        } catch (e: Exception) {
            println("Error loading transactions: ${e.message}")
            emptyList()
        }
    }

    fun saveTransactions(transactions: List<Transaction>) {
        val file = File(TRANSACTIONS_FILE)
        try {
            file.bufferedWriter().use { writer ->
                transactions.forEach { transaction ->
                    writer.write("${transaction.id}|${transaction.type}|${transaction.amount}|${transaction.timestamp}|${transaction.category.id}")
                    writer.newLine()
                }
            }
        } catch (e: Exception) {
            println("Error saving transactions: ${e.message}")
        }
    }

    fun loadCounters() {
        val file = File(COUNTERS_FILE)
        if (!file.exists()) return

        try {
            val lines = file.readLines()
            if (lines.size >= 2) {
                val categoryCounter = lines[0].toIntOrNull() ?: 0
                val transactionCounter = lines[1].toIntOrNull() ?: 0

                CategoryIdGenerator.resetCounter(categoryCounter)
                TransactionIdGenerator.resetCounter(transactionCounter)
            }
        } catch (e: Exception) {
            println("Error loading counters: ${e.message}")
        }
    }

    fun saveCounters() {
        val file = File(COUNTERS_FILE)
        try {
            file.bufferedWriter().use { writer ->
                writer.write(CategoryIdGenerator.getCurrentCounter().toString())
                writer.newLine()
                writer.write(TransactionIdGenerator.getCurrentCounter().toString())
            }
        } catch (e: Exception) {
            println("Error saving counters: ${e.message}")
        }
    }
}