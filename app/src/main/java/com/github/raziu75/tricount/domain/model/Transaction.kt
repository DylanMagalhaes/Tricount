package com.github.raziu75.tricount.domain.model

data class Transaction(
    val id: Long = 0,
    val amountInCents: Int,
    val title: String,
    val payer: Participant,
    val concernedParticipants: List<Participant>
) {
    data class Participant(
        val id: Long,
        val name: String,
    )
}
