package com.peacework.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Trail(
    val userId: String,
    val message: String,
    val lastSentDate: String,
    val isSeen: Boolean
)
