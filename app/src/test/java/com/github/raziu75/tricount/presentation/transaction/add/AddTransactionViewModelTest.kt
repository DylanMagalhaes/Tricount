package com.github.raziu75.tricount.presentation.transaction.add

import androidx.lifecycle.viewModelScope
import com.github.raziu75.tricount.common.TestDispatcherRule
import com.github.raziu75.tricount.domain.model.Transaction.Participant
import com.github.raziu75.tricount.domain.usecases.FetchParticipantListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class AddTransactionViewModelTest {
    @get: Rule val dispatcherRule = TestDispatcherRule()

    private val fetchParticipantListUseCase: FetchParticipantListUseCase = mockk(relaxed = true)

    private fun viewModel() = AddTransactionViewModel(
        fetchParticipantListUseCase = fetchParticipantListUseCase
    )

    @Test
    fun `on amount input change, should update amount value`() {
        //GIVEN
        val value = "10"
        val viewModel = viewModel()
        //WHEN
        viewModel.onAmountInputChange(value)

        //THEN
        Assert.assertEquals(value, viewModel.uiState.value.amount)
    }

    @Test
    fun `on title input change, should update amount value`() {
        //GIVEN
        val value = "gas"
        val viewModel = viewModel()
        //WHEN
        viewModel.onTitleInputChange(value)

        //THEN
        Assert.assertEquals(value, viewModel.uiState.value.title)
    }

    @Test
    fun `on payer selected, should update payer value`() {
        //GIVEN
        val participants = Participant(0, "Sony")
        val viewModel = viewModel()
        //WHEN
        viewModel.onSelectPayer(participants)

        //THEN
        Assert.assertEquals(participants, viewModel.uiState.value.payerSelectionState.selectedPayer)
    }

    @Test
    fun `on init, should show show participant list in dropDown menu`() {
        //GIVEN
        val participants = listOf(Participant(0, "Melwin"))
        coEvery { fetchParticipantListUseCase() } returns participants

        //WHEN
        val viewModel = viewModel()

        //THEN
        Assert.assertEquals(
            participants, viewModel.uiState.value.payerSelectionState.availablePayerList
        )
    }

    @Test
    fun `on init, should show participants`() {
        // GIVEN
        val participantA = Participant(0, "Melwin")
        val participantB = Participant(1, "Jules")

        val participants = listOf(participantA, participantB)
        coEvery { fetchParticipantListUseCase() } returns participants

        // WHEN
        val viewModel = viewModel()

        // THEN
        Assert.assertEquals(
            mapOf(
                participantA to false,
                participantB to false,
            ),
            viewModel.uiState.value.payerSelectionState.concernedParticipants
        )
    }

    @Test
    fun `on dismiss payer selection dropDown menu, should hide dropDown menu`() {
        //GIVEN
        val viewModel = viewModel()

        //WHEN
        viewModel.onDismissPayerSelectionDropdownMenu()

        //THEN
        Assert.assertFalse(viewModel.uiState.value.payerSelectionState.dropDownExpanded)
    }

    @Test
    fun `on dropDown menu click, should show dropDown menu`() {
        //GIVEN
        val viewModel = viewModel()

        //WHEN
        viewModel.onDropDownMenuClick()

        //THEN
        Assert.assertTrue(viewModel.uiState.value.payerSelectionState.dropDownExpanded)
    }

    @Test
    fun `on concerned participant check changed, should change value`() {
        //GIVEN
        val participantA = Participant(0, "Melwin")
        val participantB = Participant(1, "Jules")

        val participants = listOf(participantA, participantB)
        coEvery { fetchParticipantListUseCase() } returns participants


        val viewModel = viewModel()

        //WHEN
        viewModel.onConcernedParticipantCheckChanged(participantA)

        //THEN
        val map = viewModel.uiState.value.payerSelectionState.concernedParticipants
        Assert.assertTrue(map[participantA]!!)
    }

    @Test
    fun `on selected payer, should dismiss dropDown menu`() {
        //GIVEN
        val viewModel = viewModel()
        val participantA = Participant(0, "Melwin")

        viewModel.onDropDownMenuClick()

        //WHEN
        viewModel.onSelectPayer(participantA)

        //THEN
        Assert.assertFalse(viewModel.uiState.value.payerSelectionState.dropDownExpanded)
    }

    @Test
    fun`on selected payer, should mark payer as concerned participant`() {
        //GIVEN
        val participantA = Participant(0, "Melwin")
        val participantB = Participant(1, "Jules")

        val participants = listOf(participantA, participantB)
        coEvery { fetchParticipantListUseCase() } returns participants

        val viewModel = viewModel()

        //WHEN
        viewModel.onSelectPayer(participantA)

        //THEN
        Assert.assertTrue(viewModel.uiState.value.payerSelectionState.concernedParticipants[participantA]!!)

    }
}
