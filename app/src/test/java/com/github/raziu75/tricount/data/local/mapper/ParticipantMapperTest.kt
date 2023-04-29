package com.github.raziu75.tricount.data.local.mapper

import com.github.raziu75.tricount.data.local.entity.ParticipantEntity
import com.github.raziu75.tricount.domain.model.Transaction
import kotlin.random.Random
import org.junit.Assert.assertEquals
import org.junit.Test

class ParticipantMapperTest {

    @Test
    fun `should map to participant domain model`() {
        //GIVEN
        val entity = ParticipantEntity(
            participantId = Random.nextLong(),
            name = "Sonny Moore",
        )

        //WHEN
        val actual = entity.toDomain()

        //THEN
        assertEquals(
            Transaction.Participant(
                id = entity.participantId,
                name = entity.name,
            ),
            actual
        )
    }
}