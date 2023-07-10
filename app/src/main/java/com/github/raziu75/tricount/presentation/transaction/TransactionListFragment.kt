package com.github.raziu75.tricount.presentation.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.raziu75.tricount.presentation.transaction.list.TransactionListScreen

class TransactionListFragment : Fragment() {

    private val viewModel: TransactionListViewModel by viewModels()

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

                    TransactionListScreen(uiState)
                }
            }
        }

    companion object {
        const val TAG = "TransactionListFragment"

        fun newInstance(): Fragment {
            return TransactionListFragment()
        }
    }
}