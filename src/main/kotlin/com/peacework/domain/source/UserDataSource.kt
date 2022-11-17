package com.peacework.domain.source

import com.peacework.domain.model.User

interface UserDataSource {
    suspend fun insertUser(user: User)
    suspend fun getUserById(id: String): User
    suspend fun updateUser(id: String, user: User)
    suspend fun deleteUser(id: String)
}