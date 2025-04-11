package org.qudus.squad.logic.models

data class Category(
    val id: Int = CategoryIdGenerator.nextId(),
    val name: String
)

object CategoryIdGenerator {
    private var counter = 0
    fun nextId(): Int = ++counter
}