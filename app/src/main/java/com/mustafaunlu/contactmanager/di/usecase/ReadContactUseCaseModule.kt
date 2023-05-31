package com.mustafaunlu.contactmanager.di.usecase

import com.mustafaunlu.contactmanager.domain.usecase.read_contact.GetContactUseCaseImpl
import com.mustafaunlu.contactmanager.domain.usecase.read_contact.GetContactsUseCase
import com.mustafaunlu.contactmanager.domain.usecase.read_contact.GetSearchResultsUseCase
import com.mustafaunlu.contactmanager.domain.usecase.read_contact.GetSearchResultsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ReadContactUseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindReadContactUseCase(readContactUseCaseImpl: GetContactUseCaseImpl): GetContactsUseCase
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class GetSearchResultsUseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindGetSearchResultsUseCase(getSearchResultsUseCaseImpl: GetSearchResultsUseCaseImpl): GetSearchResultsUseCase
}
