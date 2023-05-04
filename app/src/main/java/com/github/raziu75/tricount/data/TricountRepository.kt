package com.github.raziu75.tricount.data

import com.github.raziu75.tricount.data.local.dao.TricountDao
import com.github.raziu75.tricount.data.local.entity.ParticipantEntity
import com.github.raziu75.tricount.data.local.entity.TransactionEntity
import com.github.raziu75.tricount.data.local.entity.relation.TransactionParticipantCrossRef
import com.github.raziu75.tricount.data.local.mapper.toDomain
import com.github.raziu75.tricount.domain.model.Transaction
import com.github.raziu75.tricount.domain.model.Transaction.Participant
import javax.inject.Inject

class TricountRepository @Inject constructor(private val dao: TricountDao) {

    suspend fun createTransaction(transaction: Transaction): Transaction {
        val transactionId = dao.createTransaction(
            TransactionEntity(
                title = transaction.title,
                amountInCents = transaction.amountInCents,
                payerId = transaction.payer.id,
            )
        )


        val transactionParticipantsCrossRef =
            transaction.participants.map { participant ->
                TransactionParticipantCrossRef(
                    transactionId = transactionId,
                    participantId = participant.id,
                )
            }

        dao.createTransactionParticipantsCrossRef(transactionParticipantsCrossRef)

        dao.updateTransactionPayer(
            transactionId = transactionId,
            payerId = transaction.payer.id,
        )

        return transaction.copy(id = transactionId)
    }

    suspend fun getTransaction(id: Long): Transaction? {
        val transactionWithParticipant = dao.getTransactionWithParticipants(id)

        val transaction = transactionWithParticipant?.transaction ?: return null
        val participants = transactionWithParticipant.participants
        val payer = transactionWithParticipant.payer


        return transaction.toDomain(
            payer = payer.toDomain(),
            participants = participants.map(ParticipantEntity::toDomain)
        )
    }

    suspend fun createParticipant(name: String): Participant {
        val participantId = dao.createParticipant(participant = ParticipantEntity(name = name))
        return Participant(participantId, name)
    }

    suspend fun deleteParticipant(participant: Participant) {
        dao.deleteParticipant(participant.id)
    }

    suspend fun getParticipants(): List<Participant> =
        dao.getAllParticipants().map(ParticipantEntity::toDomain)
}
