package com.github.raziu75.tricount.domain.model

data class Transaction(
    val id: Long,
    val amountInCents: Int,
    val title: String,
    val payer: Participant,
    val participants: List<Participant>
) {
    data class Participant(
        val id: Long,
        val name: String,
    )
}
