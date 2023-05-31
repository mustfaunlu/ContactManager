package com.mustafaunlu.contactmanager.domain.usecase.create_contact

import com.mustafaunlu.contactmanager.domain.entity.ContactEntity
import com.mustafaunlu.contactmanager.domain.repository.ContactRepository
import javax.inject.Inject

class InsertContactUseCaseImpl @Inject constructor(private val contactRepository: ContactRepository) :
    InsertContactUseCase {
    override suspend fun invoke(contact: ContactEntity) {
        contactRepository.insertContact(contact)
    }
}
