package com.mustafaunlu.contactmanager.domain.usecase.update_contact

import com.mustafaunlu.contactmanager.domain.entity.ContactEntity

interface UpdateContactUseCase {
    suspend operator fun invoke(contact: ContactEntity)
}
