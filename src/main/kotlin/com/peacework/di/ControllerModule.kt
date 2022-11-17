package com.peacework.di

import com.peacework.controller.UserControllerImpl
import com.peacework.domain.controller.UserController
import org.koin.dsl.module

val controllerModule = module {
    single<UserController> { UserControllerImpl(get()) }
}
