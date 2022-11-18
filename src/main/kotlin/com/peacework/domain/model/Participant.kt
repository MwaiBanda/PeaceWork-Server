package com.peacework.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Participant(
    val userId: String,
    val username: String
)
