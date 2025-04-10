package org.qudus.squad.testCases


fun main() {

    check(
        name = "when enter Transaction amount above 0 and not equal negative zero,then return true",
        result = true,
        correctResult = true,
    )

    check(
        name = "when user try to enter Transaction amount using words, then return false",
        result = true,
        correctResult = false,
    )

    check(
        name = "when enter Transaction amount with negative number, then return false",
        result = true,
        correctResult = false,
    )


    check(
        name = "when enter transaction amount with empty input, then return false",
        result = true,
        correctResult = true,
    )

    check(
        name = "when user selects  Transaction category with whitespace, then return true",
        result = true,
        correctResult = true,
    )

    check(
        name = "when user enters a Transaction category name with valid characters and spaces, then return true",
        result = true,
        correctResult = true,
    )


    check(
        name = "when user selects a Transaction category not in the list, then return false",
        result = true,
        correctResult = false,
    )


    check(
        name = "when user enter input empty Transaction category, then return false",
        result = true,
        correctResult = false,
    )


    check(
        name = "when user edit Transaction time but enter bad date format, then return false",
        result = true,
        correctResult = false,
    )


    check(
        name = "given user date month not in normal range for month when edit Transaction time, then return false",
        result = true,
        correctResult = false,
    )


    check(
        name = "given user date month in normal range for month with correct format edit Transaction time, then return false",
        result = true,
        correctResult = true,
    )


    check(
        name = "when user enters deposit as Transaction time Type, then return true",
        result = true,
        correctResult = true,
    )

    check(
        name = "when user enters withdraw as Transaction time Type, then return true",
        result = true,
        correctResult = true,
    )

    check(
        name = "when user enters Deposit with capital D, then return true",
        result = false,
        correctResult = true,
    )

    check(
        name = "when user enters 'WITHDRAW' in all caps, then return true",
        result = false,
        correctResult = true,
    )


    check(
        name = "when user enters 'deposit ' with trailing space, then return false",
        result = false,
        correctResult = false,
    )


    check(
        name = "when user enters 'dep0sit' with a type, then return false",
        result = false,
        correctResult = false,
    )


}


fun check(name: String, result: Boolean, correctResult: Boolean) {
    if (result == correctResult) println("Success - $name")
    else println("Failed - $name")
}