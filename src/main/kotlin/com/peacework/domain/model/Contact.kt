package com.peacework.domain.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Contact(
    @BsonId
    val id: String = ObjectId().toString(),
    val userId: String,
    val username: String
)
