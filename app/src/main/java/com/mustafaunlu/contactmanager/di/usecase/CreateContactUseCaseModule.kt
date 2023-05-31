package com.mustafaunlu.contactmanager.di.usecase

import com.mustafaunlu.contactmanager.domain.usecase.create_contact.InsertContactUseCase
import com.mustafaunlu.contactmanager.domain.usecase.create_contact.InsertContactUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class CreateContactUseCaseModule {
    @Binds
    @ViewModelScoped
    abstract fun bindInsertContactUseCase(insertContactUseCaseImpl: InsertContactUseCaseImpl): InsertContactUseCase
}