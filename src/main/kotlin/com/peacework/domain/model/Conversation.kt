package com.peacework.domain.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Conversation(
    @BsonId
    val id: String = ObjectId().toString(),
    val participants: List<Participant>,
    val trail: Trail,
    val timestamp: Long
)
