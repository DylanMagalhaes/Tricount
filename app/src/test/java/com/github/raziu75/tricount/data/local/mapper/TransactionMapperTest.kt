package com.github.raziu75.tricount.data.local.mapper

import com.github.raziu75.tricount.data.local.entity.TransactionEntity
import com.github.raziu75.tricount.domain.model.Transaction
import com.github.raziu75.tricount.domain.model.Transaction.Participant
import kotlin.random.Random
import org.junit.Assert
import org.junit.Test


class TransactionMapperTest {

    @Test
    fun `should map to transaction domain model`() {
        //GIVEN
        val transactionEntity = TransactionEntity(
            transactionId = 0,
            title = "Cheese restaurant",
            amountInCents = 157_95,
            payerId = Random.nextLong(),
        )

        val payer = Participant(id = 1, name = "Sonny Moore")

        val participants =
            listOf(
                payer,
                Participant(id = 2, name = "Fred Again"),
                Participant(id = 3, name = "Four Tet"),
            )

        //WHEN
        val actual = transactionEntity.toDomain(payer, participants)

        //THEN
        Assert.assertEquals(
            Transaction(
                id = transactionEntity.transactionId,
                amountInCents = transactionEntity.amountInCents,
                title = transactionEntity.title,
                payer = payer,
                participants = participants,
            ),
            actual,
        )
    }
}