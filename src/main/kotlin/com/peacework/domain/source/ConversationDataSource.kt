package com.peacework.domain.source

import com.peacework.domain.model.Conversation
import com.peacework.domain.model.Trail

interface ConversationDataSource {
    suspend fun getConversationsByUserID(userID: String): List<Conversation>
    suspend fun insertConversation(conversation: Conversation)
    suspend fun updateTrail(conversationId: String, trail: Trail)
    suspend fun deleteConversation(conversationId: String)
}