package com.peacework.data.ui

import kotlinx.serialization.Serializable
import kotlin.collections.List

@Serializable
data class HorizontalListComponent(
    val type: String,
    val row: List<ListItemComponent>
)

