package com.mustafaunlu.contactmanager.di.usecase

import com.mustafaunlu.contactmanager.domain.usecase.update_contact.UpdateContactUseCase
import com.mustafaunlu.contactmanager.domain.usecase.update_contact.UpdateContactUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UpdateContactUseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindUpdateContactUseCase(updateContactUseCaseImpl: UpdateContactUseCaseImpl): UpdateContactUseCase
}
