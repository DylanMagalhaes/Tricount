package com.github.raziu75.tricount.presentation.participant.list.usecases

import com.github.raziu75.tricount.data.TricountRepository
import com.github.raziu75.tricount.domain.model.Transaction
import com.github.raziu75.tricount.domain.usecases.GetTransactionUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetTransactionUseCaseTest{
    @Test
    fun `should get a transaction`() = runTest {
        // GIVEN
        val repository: TricountRepository = mockk(relaxed = true)
        val participant = Transaction.Participant(id = 0, name = "sony")
        val transaction = Transaction(
            id = 0,
            title = "course",
            amountInCents = 500,
            concernedParticipants = emptyList(),
            payer = participant)
        val useCase = GetTransactionUseCase(repository)
        coEvery { repository.getTransaction(transaction.id) } returns transaction

        // WHEN
        val actualResult = useCase.invoke(transaction.id)

        // THEN
        assertEquals(transaction, actualResult)
    }
}