package com.peacework.controller

import com.peacework.domain.controller.UserController
import com.peacework.domain.model.User
import com.peacework.domain.source.UserDataSource

class UserControllerImpl(
    private val userDataSource: UserDataSource
): UserController {
    override suspend fun postUser(user: User) {
        userDataSource.insertUser(user)
    }

    override suspend fun getUserById(id: String): User {
        return userDataSource.getUserById(id)
    }

}