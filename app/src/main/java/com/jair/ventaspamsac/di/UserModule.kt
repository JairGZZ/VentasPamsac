package com.jair.ventaspamsac.di

import com.google.firebase.auth.FirebaseAuth
import com.jair.ventaspamsac.data.database.repository.states.UserSession
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    @Singleton
    fun provideUserSession(auth: FirebaseAuth): UserSession = UserSession(auth)
}