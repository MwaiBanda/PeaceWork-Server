package com.peacework.data.ui

import kotlinx.serialization.Serializable

@Serializable
data class Action(
    val key: String,
    val type: String,
    val signals: List<Signal>? = null
)