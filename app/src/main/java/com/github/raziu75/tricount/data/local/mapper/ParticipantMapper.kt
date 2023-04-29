package com.github.raziu75.tricount.data.local.mapper

import com.github.raziu75.tricount.data.local.entity.ParticipantEntity
import com.github.raziu75.tricount.domain.model.Transaction.Participant

fun ParticipantEntity.toDomain() = Participant(participantId, name)