package com.github.raziu75.tricount.presentation.participant.list.usecases

import com.github.raziu75.tricount.data.TricountRepository
import com.github.raziu75.tricount.domain.model.Transaction
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class FetchParticipantListUseCaseTest {
    @Test
    fun `should fetch participant`() = runTest {
        // GIVEN
        val participant = Transaction.Participant(1, "Sonny")
        val participant2 = Transaction.Participant(2, "Skrilex")
        val listParticipant = listOf(participant, participant2)

        val repository: TricountRepository = mockk()
        coEvery { repository.getParticipants() } returns listParticipant
        val useCase = FetchParticipantListUseCase(repository)

        // WHEN
        useCase()

        // THEN
        assertEquals(listParticipant, repository.getParticipants())
    }
}