package com.peacework.domain.controller

import com.peacework.domain.model.Conversation
import com.peacework.domain.model.Trail

interface ConversationController {
    suspend fun getConversationsByUserID(userID: String): List<Conversation>
    suspend fun postConversation(conversation: Conversation)
    suspend fun deleteConversation(conversationId: String)
    suspend fun updateTrail(conversationId: String, trail: Trail)
}