package com.peacework.domain.ui

import kotlinx.serialization.Serializable

@Serializable
data class IconPack(
    val icons: HashMap<String, String>
)
