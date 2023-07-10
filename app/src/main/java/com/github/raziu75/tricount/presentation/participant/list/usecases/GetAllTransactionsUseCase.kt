package com.github.raziu75.tricount.presentation.participant.list.usecases

import com.github.raziu75.tricount.data.TricountRepository
import com.github.raziu75.tricount.domain.model.Transaction
import javax.inject.Inject

class GetAllTransactionsUseCase
@Inject constructor(
    private val repository: TricountRepository
) {
    suspend operator fun invoke(): List<Transaction>{
        return repository.getAllTransactions()
    }
}