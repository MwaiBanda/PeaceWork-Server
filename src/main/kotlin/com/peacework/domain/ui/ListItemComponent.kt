package com.peacework.domain.ui

import kotlinx.serialization.Serializable

@Serializable
data class ListItemComponent(
    val id: Int,
    val name: String,
    val imageURL: String
)