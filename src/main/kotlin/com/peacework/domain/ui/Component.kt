package com.peacework.domain.ui

import kotlinx.serialization.Serializable

@Serializable
data class Component(
    val type: String,
    val data: HashMap<String, String>? = null,
    val actions: List<Action>? = null
)

