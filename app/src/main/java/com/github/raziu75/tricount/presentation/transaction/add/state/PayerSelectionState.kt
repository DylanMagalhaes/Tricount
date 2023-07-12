package com.github.raziu75.tricount.presentation.transaction.add.state

import com.github.raziu75.tricount.domain.model.Transaction.Participant

data class PayerSelectionState(
    val availablePayerList: List<Participant> = emptyList(),
    val selectedPayer: Participant? = null,
    val dropDownExpanded: Boolean = false,
    val concernedParticipants: Map<Participant, Boolean> = emptyMap(),
)
