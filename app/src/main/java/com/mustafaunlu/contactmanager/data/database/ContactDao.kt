package com.mustafaunlu.contactmanager.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mustafaunlu.contactmanager.domain.entity.ContactEntity

@Dao
interface ContactDao {
    @Insert
    fun insertContact(contact: ContactEntity)

    @Delete
    fun deleteContact(contact: ContactEntity)

    @Update
    fun updateContact(contact: ContactEntity)

    @Query("SELECT * FROM contact ORDER BY id DESC LIMIT 10")
    fun getLastTenContacts(): List<ContactEntity>

    @Query("SELECT * FROM contact WHERE id = :id")
    fun getContactById(id: Int): ContactEntity

    @Query("SELECT * FROM contact WHERE `group` = :group")
    fun getContactsByGroup(group: String): List<ContactEntity>

    @Query("SELECT * FROM contact WHERE name LIKE '%' || :search || '%' OR surname LIKE '%' || :search || '%' OR phone LIKE '%' || :search || '%' OR email LIKE '%' || :search || '%' OR `group` LIKE '%' || :search || '%'")
    fun getSearchResults(search: String): List<ContactEntity>
}
