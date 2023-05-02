package com.github.raziu75.tricount.presentation.participant.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddParticipantFragment : BottomSheetDialogFragment() {

    private val viewModel: AddParticipantViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        ComposeView(requireContext()).apply {
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnLifecycleDestroyed(
                    viewLifecycleOwner
                )
            )

            setContent {
                MaterialTheme {
                    val uiState by viewModel.uiState.collectAsState()

                    AddParticipantScreen(
                        nameValue = uiState.nameValue,
                        onNameChange = { viewModel.onNameInputChange(it) },
                        onAddButtonClick = { viewModel.onSubmitClick() }
                    )
                }
            }
        }

    companion object {
        private const val TAG = "AddParticipantFragment"

        fun show(fragmentManager: FragmentManager) {
            AddParticipantFragment().show(fragmentManager, TAG)
        }
    }
}