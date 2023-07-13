package com.github.raziu75.tricount.presentation.participant.list.usecases

import com.github.raziu75.tricount.data.TricountRepository
import com.github.raziu75.tricount.domain.model.Transaction
import com.github.raziu75.tricount.domain.usecases.CreateTransactionUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CreateTransactionUseCaseTest{
    @Test
    fun `should create a transaction`() = runTest {
        // GIVEN
        val repository: TricountRepository = mockk(relaxed = true)
        val useCase = CreateTransactionUseCase(repository)
        val participant = Transaction.Participant(id = 0, name = "sony")
        val transaction = Transaction(
            id = 0,
            title = "course",
            amountInCents = 500,
            concernedParticipants = emptyList(),
            payer = participant)

        // WHEN
        useCase.invoke(transaction)

        // THEN
        coVerify { repository.createTransaction(transaction) }
    }
}