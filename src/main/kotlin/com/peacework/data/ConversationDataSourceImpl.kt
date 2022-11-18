package com.peacework.data

import com.peacework.domain.model.Conversation
import com.peacework.domain.model.Participant
import com.peacework.domain.model.Trail
import com.peacework.domain.source.ConversationDataSource
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.div
import org.litote.kmongo.eq
import org.litote.kmongo.set
import org.litote.kmongo.setTo

class ConversationDataSourceImpl(
    database: CoroutineDatabase
): ConversationDataSource {
    private val conversationCollection: CoroutineCollection<Conversation> = database.getCollection()

    override suspend fun getConversationsByUserID(userID: String): List<Conversation> {
        return conversationCollection.find( Conversation::participants / Participant::userId eq userID).toList()
    }

    override suspend fun insertConversation(conversation: Conversation) {
        conversationCollection.insertOne(conversation)
    }

    override suspend fun updateTrail(conversationId: String, trail: Trail) {
        conversationCollection.updateOne(Conversation::id eq conversationId, set(
            Conversation::trail / Trail::userId setTo trail.userId,
            Conversation::trail / Trail::message setTo trail.message,
            Conversation::trail / Trail::lastSentDate setTo trail.lastSentDate,
            Conversation::trail / Trail::isSeen setTo trail.isSeen,
        )
        )
    }

    override suspend fun deleteConversation(conversationId: String) {
        conversationCollection.deleteOne(Conversation::id eq conversationId)
    }
}