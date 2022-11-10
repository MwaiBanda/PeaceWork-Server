package com.peacework.data.ui

import kotlinx.serialization.Serializable

@Serializable
data class PageComponent(
    val pageTitle: String,
    val components: List<Component>
)