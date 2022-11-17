package com.peacework.domain.controller

import com.peacework.domain.model.User

interface UserController {
    suspend fun postUser(user: User)
    suspend fun getUserById(id: String): User
}