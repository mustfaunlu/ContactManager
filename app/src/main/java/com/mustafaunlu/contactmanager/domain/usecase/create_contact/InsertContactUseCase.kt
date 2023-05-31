package com.mustafaunlu.contactmanager.domain.usecase.create_contact

import com.mustafaunlu.contactmanager.domain.entity.ContactEntity

interface InsertContactUseCase {
    suspend operator fun invoke(contact: ContactEntity)
}