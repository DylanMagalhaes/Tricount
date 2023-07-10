package com.github.raziu75.tricount.presentation.participant.list.usecases

import com.github.raziu75.tricount.data.TricountRepository
import com.github.raziu75.tricount.domain.model.Transaction
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class GetAllTransactionsUseCaseTest {
    @Test
    fun `should get all transactions`() = runTest {
        // GIVEN
        val participant = Transaction.Participant(id = 0, name = "sony")
        val transaction = Transaction(
            id = 0,
            title = "course",
            amountInCents = 500,
            participants = emptyList(),
            payer = participant
        )
        val transaction2 = Transaction(
            id = 1,
            title = "course",
            amountInCents = 500,
            participants = emptyList(),
            payer = participant
        )

        val listTransaction = listOf(transaction,transaction2)
        val repository: TricountRepository = mockk()
        coEvery { repository.getAllTransactions() } returns listTransaction
        val useCase = GetAllTransactionsUseCase(repository)

        // WHEN
        val actual = useCase.invoke()

        // THEN
        assertEquals(listTransaction, actual)
    }
}