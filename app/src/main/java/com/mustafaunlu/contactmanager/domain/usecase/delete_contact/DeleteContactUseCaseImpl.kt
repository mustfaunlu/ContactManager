package com.mustafaunlu.contactmanager.domain.usecase.delete_contact

import com.mustafaunlu.contactmanager.domain.entity.ContactEntity
import com.mustafaunlu.contactmanager.domain.repository.ContactRepository
import javax.inject.Inject

class DeleteContactUseCaseImpl @Inject constructor(private val contactRepository: ContactRepository) : DeleteContactUseCase {
    override suspend fun invoke(contact: ContactEntity) {
        contactRepository.deleteContact(contact)
    }
}
