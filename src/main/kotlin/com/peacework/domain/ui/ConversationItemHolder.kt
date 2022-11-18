package com.peacework.domain.ui

import com.peacework.domain.model.Conversation
import kotlinx.serialization.Serializable

@Serializable
data class ConversationItemHolder(
    val items: List<Conversation>
)