package com.github.raziu75.tricount.presentation.participant.list.usecases

import com.github.raziu75.tricount.data.TricountRepository
import com.github.raziu75.tricount.domain.usecases.AddParticipantUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AddParticipantUseCaseTest {
    @Test
    fun `should add participant`() = runTest {
        // GIVEN
        val repository: TricountRepository = mockk(relaxed = true)
        val useCase = AddParticipantUseCase(repository)

        val participant = "Melwin"

        // WHEN
        useCase.invoke(participant)

        // THEN
        coVerify { repository.createParticipant(participant) }
    }
}
