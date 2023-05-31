package com.mustafaunlu.contactmanager.domain.repository

import com.mustafaunlu.contactmanager.common.ResponseStateHandle
import com.mustafaunlu.contactmanager.domain.entity.ContactEntity
import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    suspend fun insertContact(contact: ContactEntity)
    suspend fun deleteContact(contact: ContactEntity)
    suspend fun updateContact(contact: ContactEntity)
    suspend fun getLastTenContacts(): Flow<ResponseStateHandle<List<ContactEntity>>>
    suspend fun getContactById(id: Int): Flow<ResponseStateHandle<ContactEntity>>
    suspend fun getContactsByGroup(group: String): Flow<ResponseStateHandle<List<ContactEntity>>>
    suspend fun getSearchResults(search: String): Flow<ResponseStateHandle<List<ContactEntity>>>
}
