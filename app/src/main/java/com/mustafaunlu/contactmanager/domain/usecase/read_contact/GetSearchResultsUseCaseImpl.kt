package com.mustafaunlu.contactmanager.domain.usecase.read_contact

import com.mustafaunlu.contactmanager.common.ResponseStateHandle
import com.mustafaunlu.contactmanager.domain.entity.ContactEntity
import com.mustafaunlu.contactmanager.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchResultsUseCaseImpl @Inject constructor(
    private val contactRepository: ContactRepository
) : GetSearchResultsUseCase {
    override suspend fun invoke(search: String): Flow<ResponseStateHandle<List<ContactEntity>>> {
        return contactRepository.getSearchResults(search)
    }
}
