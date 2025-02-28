package com.jair.ventaspamsac.di

import com.jair.ventaspamsac.data.database.interfaces.IAuthRepository
import com.jair.ventaspamsac.data.database.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthBindingModule {

    @Binds
    abstract fun bindAuthRepository(authRepository: AuthRepository): IAuthRepository
}