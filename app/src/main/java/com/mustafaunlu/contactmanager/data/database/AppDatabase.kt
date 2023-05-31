package com.mustafaunlu.contactmanager.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mustafaunlu.contactmanager.domain.entity.ContactEntity

@Database(entities = [ContactEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}
