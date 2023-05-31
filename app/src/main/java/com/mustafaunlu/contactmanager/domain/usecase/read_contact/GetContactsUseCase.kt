package com.mustafaunlu.contactmanager.domain.usecase.read_contact

import com.mustafaunlu.contactmanager.common.ResponseStateHandle
import com.mustafaunlu.contactmanager.domain.entity.ContactEntity
import kotlinx.coroutines.flow.Flow

interface GetContactsUseCase {
    suspend operator fun invoke(): Flow<ResponseStateHandle<List<ContactEntity>>>

    suspend operator fun invoke(id: Int): Flow<ResponseStateHandle<ContactEntity>>

    suspend operator fun invoke(group: String): Flow<ResponseStateHandle<List<ContactEntity>>>
}
