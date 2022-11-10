package com.peacework.data.ui

import kotlinx.serialization.Serializable

@Serializable
data class VerticalListComponent(
    val type: String,
    val items: List<ListItemComponent>
)