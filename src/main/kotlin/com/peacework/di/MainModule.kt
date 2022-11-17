package com.peacework.di

import com.peacework.data.UserDataSourceImpl
import com.peacework.domain.source.UserDataSource
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {
    single {
        KMongo.createClient()
            .coroutine
            .getDatabase("peacework_db")
    }
}