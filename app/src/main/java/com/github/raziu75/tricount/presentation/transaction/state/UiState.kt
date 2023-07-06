package com.github.raziu75.tricount.presentation.transaction.state

import android.view.SurfaceControl.Transaction

data class UiState(
    val payer: Transaction,
    val addParticipantBottomSheetVisible: Boolean = false,
    val addParticipantSubmitButtonEnabled: Boolean = false,
)

