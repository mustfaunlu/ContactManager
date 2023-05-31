package com.mustafaunlu.contactmanager.data.repository

import com.mustafaunlu.contactmanager.common.ResponseStateHandle
import com.mustafaunlu.contactmanager.data.datasource.LocalDataSource
import com.mustafaunlu.contactmanager.di.coroutine.IoDispatcher
import com.mustafaunlu.contactmanager.domain.entity.ContactEntity
import com.mustafaunlu.contactmanager.domain.repository.ContactRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ContactRepository {
    override suspend fun insertContact(contact: ContactEntity) {
        localDataSource.insertContact(contact)
    }

    override suspend fun deleteContact(contact: ContactEntity) {
        localDataSource.deleteContact(contact)
    }

    override suspend fun updateContact(contact: ContactEntity) {
        localDataSource.updateContact(contact)
    }

    override suspend fun getLastTenContacts(): Flow<ResponseStateHandle<List<ContactEntity>>> {
        return flow {
            emit(ResponseStateHandle.Loading)
            when (val response = localDataSource.getLastTenContacts()) {
                is ResponseStateHandle.Success -> {
                    emit(ResponseStateHandle.Success(response.result))
                }
                ResponseStateHandle.Loading -> {
                    emit(ResponseStateHandle.Loading)
                }
                is ResponseStateHandle.Error -> {
                    emit(ResponseStateHandle.Error(response.exception))
                }
            }

        }.flowOn(ioDispatcher)
    }

    override suspend fun getContactById(id: Int): Flow<ResponseStateHandle<ContactEntity>> {
        return flow {
            emit(ResponseStateHandle.Loading)
            when(val response = localDataSource.getContactById(id)) {
                is ResponseStateHandle.Success -> {
                    emit(ResponseStateHandle.Success(response.result))
                }
                ResponseStateHandle.Loading -> {
                    emit(ResponseStateHandle.Loading)
                }
                is ResponseStateHandle.Error -> {
                    emit(ResponseStateHandle.Error(response.exception))
                }
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun getContactsByGroup(group: String): Flow<ResponseStateHandle<List<ContactEntity>>> {
        return flow {
            emit(ResponseStateHandle.Loading)
            when(val response = localDataSource.getContactsByGroup(group)) {
                is ResponseStateHandle.Success -> {
                    emit(ResponseStateHandle.Success(response.result))
                }
                ResponseStateHandle.Loading -> {
                    emit(ResponseStateHandle.Loading)
                }
                is ResponseStateHandle.Error -> {
                    emit(ResponseStateHandle.Error(response.exception))
                }
            }
        }.flowOn(ioDispatcher)
    }

    override suspend fun getSearchResults(search: String): Flow<ResponseStateHandle<List<ContactEntity>>> {
        return flow {
            emit(ResponseStateHandle.Loading)
            when(val response = localDataSource.getSearchResults(search)) {
                is ResponseStateHandle.Success -> {
                    emit(ResponseStateHandle.Success(response.result))
                }
                ResponseStateHandle.Loading -> {
                    emit(ResponseStateHandle.Loading)
                }
                is ResponseStateHandle.Error -> {
                    emit(ResponseStateHandle.Error(response.exception))
                }
            }
        }.flowOn(ioDispatcher)
    }
}
