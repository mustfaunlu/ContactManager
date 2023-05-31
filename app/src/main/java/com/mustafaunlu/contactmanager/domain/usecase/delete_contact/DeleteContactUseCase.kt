package com.mustafaunlu.contactmanager.domain.usecase.delete_contact

import com.mustafaunlu.contactmanager.domain.entity.ContactEntity

interface DeleteContactUseCase {
    suspend operator fun invoke(contact: ContactEntity)
}