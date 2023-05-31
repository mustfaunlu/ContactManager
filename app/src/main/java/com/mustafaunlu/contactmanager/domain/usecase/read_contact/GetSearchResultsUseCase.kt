package com.mustafaunlu.contactmanager.domain.usecase.read_contact

import com.mustafaunlu.contactmanager.common.ResponseStateHandle
import com.mustafaunlu.contactmanager.domain.entity.ContactEntity
import kotlinx.coroutines.flow.Flow

interface GetSearchResultsUseCase {
    suspend operator fun invoke(search: String): Flow<ResponseStateHandle<List<ContactEntity>>>
}
