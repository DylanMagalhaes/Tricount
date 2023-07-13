package com.github.raziu75.tricount.presentation.transaction

import com.github.raziu75.tricount.common.TestDispatcherRule
import com.github.raziu75.tricount.domain.model.Transaction
import com.github.raziu75.tricount.domain.usecases.GetAllTransactionsUseCase
import com.github.raziu75.tricount.presentation.transaction.list.TransactionListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class TransactionListViewModelTest {
    @get: Rule val dispatcherRule = TestDispatcherRule()

    private val getAllTransactionsUseCase: GetAllTransactionsUseCase = mockk()

    private fun viewModel() = TransactionListViewModel(getAllTransactionsUseCase)

    @Test
    fun `on init, should show transactions`() {
        //GIVEN
        val transactions = listOf<Transaction>()
        coEvery { getAllTransactionsUseCase() } returns transactions

        //WHEN
        val viewModel = viewModel()

        //THEN
        assertEquals(transactions, viewModel.uiState.value.transactionList)
    }

    @Test
    fun `on dismiss sheet, should hide`() {
        //GIVEN
        val viewModel = viewModel()

        //WHEN
        viewModel.onDismissSheet()

        //THEN
        Assert.assertFalse(viewModel.uiState.value.addTransactionBottomSheetVisible)
    }

    @Test
    fun `on dismiss sheet, should refresh transactions`() {
        //GIVEN
        val transactions = listOf<Transaction>()
        coEvery { getAllTransactionsUseCase() } returns transactions

        val viewModel = viewModel()

        //WHEN
        viewModel.onDismissSheet()

        //THEN
        assertEquals(transactions, viewModel.uiState.value.transactionList)
    }
}
