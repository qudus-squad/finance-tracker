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
        "Valid delete :Transaction is deleted",
         dataSource.removeTransaction(1),
         true
     )
    check(
        "Invalid delete :Transaction dose not exist",
        dataSource.removeTransaction(1),
        false
    )
    val emptyDataSource=FinanceTrackerDataSourceImpl()
    check(
        "Invalid delete :No transactions to delete",
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