package com.github.raziu75.tricount.presentation.participant.list.usecases

import com.github.raziu75.tricount.data.TricountRepository
import com.github.raziu75.tricount.domain.model.Transaction
import com.github.raziu75.tricount.domain.usecases.DeleteParticipantUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DeleteParticipantUseCaseTest {

    @Test
    fun `should delete participant`() = runTest {
        // GIVEN
        val repository: TricountRepository = mockk(relaxed = true)
        val useCase = DeleteParticipantUseCase(repository)

        val participant = Transaction.Participant(1, "Sonny")

        // WHEN
        useCase(participant)

        // THEN
        coVerify { repository.deleteParticipant(participant)  }
    }
}