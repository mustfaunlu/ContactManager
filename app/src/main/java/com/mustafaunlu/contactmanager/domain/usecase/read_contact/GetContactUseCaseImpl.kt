package com.mustafaunlu.contactmanager.domain.usecase.read_contact

import com.mustafaunlu.contactmanager.common.ResponseStateHandle
import com.mustafaunlu.contactmanager.domain.entity.ContactEntity
import com.mustafaunlu.contactmanager.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetContactUseCaseImpl @Inject constructor(private val contactRepository: ContactRepository) : GetContactsUseCase {
    override suspend fun invoke(): Flow<ResponseStateHandle<List<ContactEntity>>> {
        return contactRepository.getLastTenContacts()
    }

    override suspend fun invoke(id: Int): Flow<ResponseStateHandle<ContactEntity>> {
        return contactRepository.getContactById(id)
    }

    override suspend fun invoke(group: String): Flow<ResponseStateHandle<List<ContactEntity>>> {
        return contactRepository.getContactsByGroup(group)
    }
}