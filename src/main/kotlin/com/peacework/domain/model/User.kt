package com.peacework.domain.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class User (
    @BsonId
    val id: String = ObjectId().toString(),
    var createdOn: String,
    var fullname: String,
    var email: String,
    var userId: String,
    var company: String,
    var position: String,
    var dateStarted: String,
    var contacts: List<Contact>
)

