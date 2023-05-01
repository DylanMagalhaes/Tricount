package com.github.raziu75.tricount.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnLifecycleDestroyed
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.raziu75.tricount.presentation.participant.ParticipantListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        ComposeView(requireContext()).apply {
            setViewCompositionStrategy(DisposeOnLifecycleDestroyed(viewLifecycleOwner))
            setContent {
                MaterialTheme(
                    colorScheme = lightColorScheme()
                ) {
                    val uiState by viewModel.uiState.collectAsState()

                    HomeScreen(
                        modifier = Modifier.fillMaxSize(),
                        state = uiState,
                        navigateToParticipantList = { startParticipantListUi() },
                    )
                }
            }
        }

    private fun startParticipantListUi() {
        parentFragmentManager
            .beginTransaction()
            .replace(
                android.R.id.content,
                ParticipantListFragment.newInstance(),
                ParticipantListFragment.TAG,
            )
            .commitNow()
    }

    companion object {
        const val TAG = "HomeFragment"

        fun newInstance(): Fragment {
            return HomeFragment()
        }
    }
}