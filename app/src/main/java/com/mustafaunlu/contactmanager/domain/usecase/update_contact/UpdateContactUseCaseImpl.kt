package com.mustafaunlu.contactmanager.domain.usecase.update_contact

import com.mustafaunlu.contactmanager.domain.entity.ContactEntity
import com.mustafaunlu.contactmanager.domain.repository.ContactRepository
import javax.inject.Inject

class UpdateContactUseCaseImpl @Inject constructor(private val contactRepository: ContactRepository) :
    UpdateContactUseCase {
    override suspend fun invoke(contact: ContactEntity) {
        contactRepository.updateContact(contact)
    }
}
