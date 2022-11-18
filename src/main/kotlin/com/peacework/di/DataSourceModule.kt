package com.peacework.di

import com.peacework.data.ConversationDataSourceImpl
import com.peacework.data.UserDataSourceImpl
import com.peacework.domain.source.ConversationDataSource
import com.peacework.domain.source.UserDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<UserDataSource> { UserDataSourceImpl(get()) }
    single<ConversationDataSource>{ ConversationDataSourceImpl(get()) }
}