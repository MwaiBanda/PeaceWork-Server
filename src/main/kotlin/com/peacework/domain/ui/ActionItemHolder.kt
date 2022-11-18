package com.peacework.domain.ui

import kotlinx.serialization.Serializable

@Serializable
data class ActionItemHolder(
    val items: List<ActionComponent>
)
