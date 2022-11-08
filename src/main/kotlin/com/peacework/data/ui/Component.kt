package com.peacework.data.ui

import kotlinx.serialization.Serializable

@Serializable
data class Component(
    val type: String,
    val data: HashMap<String, String>
)

