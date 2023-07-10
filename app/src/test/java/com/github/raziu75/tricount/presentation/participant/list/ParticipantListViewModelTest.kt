package com.github.raziu75.tricount.presentation.participant.list

import com.github.raziu75.tricount.common.TestDispatcherRule
import com.github.raziu75.tricount.data.TricountRepository
import com.github.raziu75.tricount.domain.model.Transaction.Participant
import com.github.raziu75.tricount.presentation.participant.list.usecases.AddParticipantUseCase
import com.github.raziu75.tricount.presentation.participant.list.usecases.FetchParticipantListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class ParticipantListViewModelTest {
    @get: Rule val dispatcherRule = TestDispatcherRule()

    private val repository: TricountRepository = mockk()
    private val fetchParticipantListUseCase: FetchParticipantListUseCase = mockk(relaxed = true)
    private val addParticipantUseCase: AddParticipantUseCase = mockk()
    private fun viewModel() = ParticipantListViewModel(
        repository = repository,
        addParticipantUseCase = addParticipantUseCase,
        fetchParticipantListUseCase = fetchParticipantListUseCase
    )

    @Test
    fun `onNameInputChange, should disable submit button when input is blank`() {
        //GIVEN
        val input = ""
        val viewModel = viewModel()

        //WHEN
        viewModel.onNameInputChange(input)

        //THEN
        assertFalse(viewModel.uiState.value.addParticipantSubmitButtonEnabled)
    }

    @Test
    fun `onNameInputChange, should update name value`() {
        //GIVEN
        val viewModel = viewModel()
        val value = "coco"

        //WHEN
        viewModel.onNameInputChange(value)

        //THEN
        assertEquals(value, viewModel.uiState.value.nameValue)
    }

    @Test
    fun `onNameInputChange, should enable submit button when input is not blank`() {
        //GIVEN
        val input = "bobi la défaite"
        val viewModel = viewModel()

        //WHEN
        viewModel.onNameInputChange(input)

        //THEN
        assertTrue(viewModel.uiState.value.addParticipantSubmitButtonEnabled)
    }

    @Test
    fun `onAddParticipantFabClick, should open bottom sheet`() {
        //GIVEN
        val viewModel = viewModel()

        //WHEN
        viewModel.onAddParticipantFabClick()

        //THEN
        assertTrue(viewModel.uiState.value.addParticipantBottomSheetVisible)
    }

    @Test
    fun `onAddParticipantDismiss, should close bottom sheet`() {
        //GIVEN
        val viewModel = viewModel()

        //WHEN
        viewModel.onAddParticipantDismiss()

        //THEN
        assertFalse(viewModel.uiState.value.addParticipantBottomSheetVisible)
    }

    @Test
    fun `on init, should show participants`() {
        //GIVEN
        val participants = listOf<Participant>()
        coEvery { fetchParticipantListUseCase() } returns participants

        //WHEN
        val viewModel = viewModel()

        //THEN
        assertEquals(participants, viewModel.uiState.value.participantList)
    }
}
