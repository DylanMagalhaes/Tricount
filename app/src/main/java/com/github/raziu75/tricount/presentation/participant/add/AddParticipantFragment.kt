package com.github.raziu75.tricount.presentation.participant.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddParticipantFragment : BottomSheetDialogFragment() {

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
                    AddParticipantScreen(
                        nameValue = "Toto", //TODO: ViewModel
                        onNameChange = {}, //TODO: ViewModel
                        onAddButtonClick = { /*TODO*/ })
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