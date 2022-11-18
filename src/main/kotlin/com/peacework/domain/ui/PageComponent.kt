package com.peacework.domain.ui

import kotlinx.serialization.Serializable

@Serializable
data class PageComponent(
    val title: String,
    val components: List<Component>,
    val metadata: PageMetadata? = null
)

