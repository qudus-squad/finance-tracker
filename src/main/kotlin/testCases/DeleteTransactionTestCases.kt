package org.qudus.squad.testCases

import org.qudus.squad.dataSource.FinanceTrackerDataSourceImpl
import org.qudus.squad.logic.models.Category
import org.qudus.squad.logic.models.Transaction
import org.qudus.squad.logic.transactions.DeleteTransaction

fun main(){
    val dataSource= FinanceTrackerDataSourceImpl()
    val transaction= Transaction(1,"Expense",100.0,2005L, Category("Food",1))
    dataSource.addTransaction(transaction)
    check(
        "given a valid transaction when it is deleted then it is removed",
        dataSource.removeTransaction(1),
        true
    )
    check(
        "given an valid transaction when deleted is attempted then it is fails",
        dataSource.removeTransaction(1),
        false
    )
    val emptyDataSource=FinanceTrackerDataSourceImpl()
    check(
        "given no transaction exist when deleted is attempted then nothing happens",
        emptyDataSource.removeTransaction(1),
        false
    )


}



fun check(value:String,result:Boolean,correctResult:Boolean){
    if (result==correctResult){
        println("Success -$value")
    }else{
        println("Failed -$value")
    }
}