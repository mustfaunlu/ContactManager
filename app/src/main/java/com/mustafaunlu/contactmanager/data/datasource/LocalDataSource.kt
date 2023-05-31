package com.mustafaunlu.contactmanager.data.datasource

import com.mustafaunlu.contactmanager.common.ResponseStateHandle
import com.mustafaunlu.contactmanager.domain.entity.ContactEntity

interface LocalDataSource {
    suspend fun insertContact(contact: ContactEntity)

    suspend fun deleteContact(contact: ContactEntity)

    suspend fun updateContact(contact: ContactEntity)

    suspend fun getLastTenContacts(): ResponseStateHandle<List<ContactEntity>>

    suspend fun getContactById(id: Int): ResponseStateHandle<ContactEntity>

    suspend fun getContactsByGroup(group: String): ResponseStateHandle<List<ContactEntity>>

    suspend fun getSearchResults(search: String): ResponseStateHandle<List<ContactEntity>>
}
