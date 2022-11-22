package com.peacework.controller

import com.peacework.domain.controller.ConversationController
import com.peacework.domain.model.Conversation
import com.peacework.domain.model.Trail
import com.peacework.domain.source.ConversationDataSource

class ConversationControllerImpl(
    private val conversationDataSource: ConversationDataSource
): ConversationController {
    override suspend fun getConversationsByUserID(userID: String): List<Conversation> {
        return conversationDataSource.getConversationsByUserID(userID)
    }

    override suspend fun postConversation(conversation: Conversation) {
        conversationDataSource.insertConversation(conversation)
    }
    override suspend fun deleteConversation(conversationId: String) {
        conversationDataSource.deleteConversation(conversationId)
    }

    override suspend fun updateTrail(conversationId: String, trail: Trail) {
        conversationDataSource.updateTrail(conversationId, trail)
    }
}