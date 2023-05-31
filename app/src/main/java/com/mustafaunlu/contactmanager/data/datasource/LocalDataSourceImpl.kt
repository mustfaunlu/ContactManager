package com.mustafaunlu.contactmanager.data.datasource

import com.mustafaunlu.contactmanager.common.ResponseStateHandle
import com.mustafaunlu.contactmanager.data.database.ContactDao
import com.mustafaunlu.contactmanager.domain.entity.ContactEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val contactDao: ContactDao) :
    LocalDataSource {
    override suspend fun insertContact(contact: ContactEntity) {
        contactDao.insertContact(contact)
    }

    override suspend fun deleteContact(contact: ContactEntity) {
        contactDao.deleteContact(contact)
    }

    override suspend fun updateContact(contact: ContactEntity) {
        contactDao.updateContact(contact)
    }

    override suspend fun getLastTenContacts(): ResponseStateHandle<List<ContactEntity>> {
        return try {
            ResponseStateHandle.Loading
            ResponseStateHandle.Success(contactDao.getLastTenContacts())
        } catch (e: Exception) {
            ResponseStateHandle.Error(e)
        }
    }

    override suspend fun getContactById(id: Int): ResponseStateHandle<ContactEntity> {
        return try {
            ResponseStateHandle.Loading
            ResponseStateHandle.Success(contactDao.getContactById(id))
        } catch (e: Exception) {
            ResponseStateHandle.Error(e)
        }
    }

    override suspend fun getContactsByGroup(group: String): ResponseStateHandle<List<ContactEntity>> {
        return try {
            ResponseStateHandle.Loading
            ResponseStateHandle.Success(contactDao.getContactsByGroup(group))
        } catch (e: Exception) {
            ResponseStateHandle.Error(e)
        }
    }

    override suspend fun getSearchResults(search: String): ResponseStateHandle<List<ContactEntity>> {
        return try {
            ResponseStateHandle.Loading
            ResponseStateHandle.Success(contactDao.getSearchResults(search))
        } catch (e: Exception) {
            ResponseStateHandle.Error(e)
        }
    }
}
