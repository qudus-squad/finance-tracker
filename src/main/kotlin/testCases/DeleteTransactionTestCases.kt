package org.qudus.squad.testCases

import org.qudus.squad.dataSource.FinanceTrackerDataSourceImpl
import org.qudus.squad.logic.models.Category
import org.qudus.squad.logic.models.Transaction
import org.qudus.squad.logic.models.TransactionType

fun main(){
    val dataSource= FinanceTrackerDataSourceImpl()
    val transaction= Transaction(1,TransactionType.Deposit,100.0,2005L, Category(1,"food"))
    dataSource.addTransaction(transaction)

    check(
        value = "given a valid transaction when it is deleted then it is removed",
        result = dataSource.removeTransaction(1),
        correctResult = true
    )
    check(
        value = "given an valid transaction when deleted is attempted then it is fails",
        result = dataSource.removeTransaction(1),
        correctResult = false
    )
    val emptyDataSource=FinanceTrackerDataSourceImpl()
    check(
        value = "given no transaction exist when deleted is attempted then nothing happens",
        result = emptyDataSource.removeTransaction(1),
        correctResult = false
    )

}

fun check(value:String,result:Boolean,correctResult:Boolean){
    if (result==correctResult){
        println("Success -$value")
    }else{
        println("Failed -$value")
    }
}