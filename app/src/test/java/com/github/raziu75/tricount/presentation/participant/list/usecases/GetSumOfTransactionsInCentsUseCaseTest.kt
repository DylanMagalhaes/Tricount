package com.github.raziu75.tricount.presentation.participant.list.usecases

import com.github.raziu75.tricount.data.TricountRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetSumOfTransactionsInCentsUseCaseTest {
    @Test
    fun `should get sum of transactions in cents`() = runTest {
        // GIVEN
        val repository: TricountRepository = mockk()
        val sumOfTransactions = 100
        coEvery { repository.getSumOfTransactionsInCents() } returns sumOfTransactions

        val useCase = GetSumOfTransactionsInCentsUseCase(repository)

        // WHEN
        val actualResult = useCase.invoke()

        // THEN
        assertEquals(sumOfTransactions, actualResult)
    }
}
