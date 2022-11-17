package com.peacework.domain.ui

import kotlinx.serialization.Serializable

@Serializable
data class Action(
    val key: String,
    val type: String,
    val data: HashMap<String, String>? = null,
    val signals: List<Signal>? = null
)