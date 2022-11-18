package com.peacework.domain.ui

import kotlinx.serialization.Serializable

@Serializable
data class ActionComponent(
    val id: String,
    val data: HashMap<String, String>
)
