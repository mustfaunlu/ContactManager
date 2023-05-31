package com.mustafaunlu.contactmanager.di.datasource

import com.mustafaunlu.contactmanager.data.datasource.LocalDataSource
import com.mustafaunlu.contactmanager.data.datasource.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class LocalDataSourceModule {
    @Binds
    @ViewModelScoped
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource
}
