package com.github.raziu75.tricount.domain.usecases

import com.github.raziu75.tricount.data.TricountRepository
import com.github.raziu75.tricount.domain.model.Transaction
import javax.inject.Inject

class GetTransactionUseCase
@Inject constructor(
    private val repository: TricountRepository
) {
    suspend operator fun invoke(id: Long): Transaction? {
        return repository.getTransaction(id)
    }
}