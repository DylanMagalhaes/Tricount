package com.github.raziu75.tricount.presentation.participant.list.usecases

import com.github.raziu75.tricount.data.TricountRepository
import com.github.raziu75.tricount.domain.model.Transaction.Participant
import javax.inject.Inject

class FetchParticipantListUseCase
@Inject constructor(
    private val repository: TricountRepository,
) {
    suspend fun invoke(): List<Participant> {
        return repository.getParticipants()
    }
}