package com.mustafaunlu.contactmanager.di.database

import android.content.Context
import androidx.room.Room
import com.mustafaunlu.contactmanager.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "contact_database")
            .build()

    @Singleton
    @Provides
    fun provideContactDao(appDatabase: AppDatabase) = appDatabase.contactDao()
}
