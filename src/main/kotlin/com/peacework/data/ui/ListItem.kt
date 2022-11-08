package com.peacework.data.ui

import kotlinx.serialization.Serializable

@Serializable
data class ListItem(
    val id: Int,
    val name: String,
    val imageURL: String
)