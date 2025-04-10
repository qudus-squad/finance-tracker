package org.qudus.squad.logic.transactions

import org.qudus.squad.dataSource.FinanceTrackerDataSourceImpl
import org.qudus.squad.logic.FinanceTrackerDataSource

class DeleteTransaction(private val dataSource: FinanceTrackerDataSourceImpl) {
    fun deleteTransaction(transactionId: Int):Boolean{
        val transaction=dataSource.getTransactionById(transactionId)
        return if(transaction !=null){
            dataSource.removeTransaction(transactionId)
            true
        }else{
            false
        }
    }


}