package com.github.raziu75.tricount.presentation.participant.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnLifecycleDestroyed
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ParticipantListFragment : Fragment() {

    private val viewModel: ParticipantListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        ComposeView(requireContext()).apply {
            setViewCompositionStrategy(DisposeOnLifecycleDestroyed(viewLifecycleOwner))
            setContent {
                MaterialTheme {
                    val uiState by viewModel.uiState.collectAsState()

                    ParticipantListScreen(
                        modifier = Modifier.fillMaxSize(),
                        state = uiState,
                        onAddParticipantFabClick = { viewModel.onAddParticipantFabClick() },
                        onAddParticipantNameInputChange = { viewModel.onNameInputChange(it) },
                        onAddParticipantSubmitClick = { viewModel.onAddParticipantSubmitClick() },
                        onAddParticipantDismiss = { viewModel.onAddParticipantDismiss() },
                        onDeleteParticipantClick = { viewModel.onDeleteParticipantClick(it) }
                    )
                }
            }
        }

    companion object {
        const val TAG = "ParticipantListFragment"

        fun newInstance(): Fragment {
            return ParticipantListFragment()
        }
    }
}