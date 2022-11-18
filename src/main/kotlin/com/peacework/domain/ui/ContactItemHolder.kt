package com.peacework.domain.ui

import com.peacework.domain.model.Contact
import kotlinx.serialization.Serializable

@Serializable
data class ContactItemHolder(
    val items: List<Contact>
)