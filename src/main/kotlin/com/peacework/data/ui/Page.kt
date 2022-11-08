package com.peacework.data.ui

import kotlinx.serialization.Serializable

@Serializable
data class Page(
    val pageTitle: String,
    val components: List<Component>
)