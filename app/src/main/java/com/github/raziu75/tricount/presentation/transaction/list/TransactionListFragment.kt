package com.github.raziu75.tricount.presentation.transaction.list

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
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.raziu75.tricount.presentation.transaction.add.AddTransactionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionListFragment : Fragment() {

    private val transactionListviewModel: TransactionListViewModel by viewModels()
    private val addTransactionViewModel: AddTransactionViewModel by viewModels()

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
                    val uiState by transactionListviewModel.uiState.collectAsState()
                    val addTransactionUiState by addTransactionViewModel.uiState.collectAsState()

                    TransactionListScreen(
                        modifier = Modifier.fillMaxSize(),
                        uiState = uiState,
                        addTransactionUiState = addTransactionUiState,
                        onAddTransactionFabClick = { transactionListviewModel.onAddTransactionFabClick() },
                        onAmountInputChange = { addTransactionViewModel.onAmountInputChange(it) },
                        onTitleInputChange = { addTransactionViewModel.onTitleInputChange(it) },
                        onSelectPayer = { addTransactionViewModel.onSelectPayer(it) },
                        onConcernedParticipantCheckChanged = { participant, _ ->
                            addTransactionViewModel.onConcernedParticipantCheckChanged(
                                participant
                            )
                        },
                        onSheetDismiss = { transactionListviewModel.onDismissSheet() },
                        onPayerSelectionDropdownClick = { addTransactionViewModel.onDropDownMenuClick() },
                        onDismissPayerSelectionDropdownMenu = { addTransactionViewModel.onDismissPayerSelectionDropdownMenu() },
                    )
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