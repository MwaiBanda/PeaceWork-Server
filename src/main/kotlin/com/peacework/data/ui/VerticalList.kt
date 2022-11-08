package com.peacework.data.ui

import kotlinx.serialization.Serializable

@Serializable
data class VerticalList(
    val type: String,
    val items: List<ListItem>
)