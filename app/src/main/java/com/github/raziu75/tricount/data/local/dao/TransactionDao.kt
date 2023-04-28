package com.github.raziu75.tricount.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.github.raziu75.tricount.data.local.entity.relation.TransactionWithParticipants

@Dao
interface TransactionDao {

    @Transaction
    @Query("SELECT * FROM `transaction` WHERE transaction_id = :transactionId")
    suspend fun getTransactionWithParticipants(transactionId: Long): List<TransactionWithParticipants>
}