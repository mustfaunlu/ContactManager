package com.mustafaunlu.contactmanager.di.usecase

import com.mustafaunlu.contactmanager.domain.usecase.delete_contact.DeleteContactUseCase
import com.mustafaunlu.contactmanager.domain.usecase.delete_contact.DeleteContactUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DeleteContactUseCasModule {

    @Binds
    @ViewModelScoped
    abstract fun bindDeleteContactUseCase(deleteContactUseCaseImpl: DeleteContactUseCaseImpl): DeleteContactUseCase
}
