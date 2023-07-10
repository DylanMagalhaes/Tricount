package com.github.raziu75.tricount.presentation.home

import com.github.raziu75.tricount.common.TestDispatcherRule
import com.github.raziu75.tricount.domain.model.Transaction.Participant
import com.github.raziu75.tricount.domain.usecases.FetchParticipantListUseCase
import com.github.raziu75.tricount.domain.usecases.GetSumOfTransactionsInCentsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    @get: Rule val dispatcherRule = TestDispatcherRule()

    private val fetchParticipantListUseCase: FetchParticipantListUseCase = mockk(relaxed = true)
    private val getSumOfTransactionsInCentsUseCase: GetSumOfTransactionsInCentsUseCase = mockk(
        relaxed = true
    )

    private fun viewModel() = HomeViewModel(
        fetchParticipantListUseCase,
        getSumOfTransactionsInCentsUseCase
    )

    @Test
    fun `on init, should show participant count`() {
        //GIVEN
        val participants = listOf(
            Participant(0, "Dylan"),
            Participant(1, "Melwin")
        )
        coEvery { fetchParticipantListUseCase() } returns participants

        //WHEN
        val viewModel = viewModel()

        //THEN
        Assert.assertEquals(participants.size, viewModel.uiState.value.participantCount)
    }

    @Test
    fun `on init, should show sum of transactions in cents`() {
        //GIVEN
        coEvery { getSumOfTransactionsInCentsUseCase() } returns 100

        //WHEN
        val viewModel = viewModel()

        //THEN
        Assert.assertEquals(100, viewModel.uiState.value.totalExpensesInCents)
    }

}