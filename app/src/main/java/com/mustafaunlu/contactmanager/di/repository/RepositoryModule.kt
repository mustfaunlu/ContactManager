package com.mustafaunlu.contactmanager.di.repository

import com.mustafaunlu.contactmanager.data.repository.ContactRepositoryImpl
import com.mustafaunlu.contactmanager.domain.repository.ContactRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindContactRepository(contactRepositoryImpl: ContactRepositoryImpl): ContactRepository
}
