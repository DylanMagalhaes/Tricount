package com.github.raziu75.tricount.data.local.mapper

import com.github.raziu75.tricount.data.local.entity.TransactionEntity
import com.github.raziu75.tricount.domain.model.Transaction

fun TransactionEntity.toDomain(
    payer: Transaction.Participant,
    participants: List<Transaction.Participant>,
) = Transaction(transactionId, amountInCents, title, payer, participants)