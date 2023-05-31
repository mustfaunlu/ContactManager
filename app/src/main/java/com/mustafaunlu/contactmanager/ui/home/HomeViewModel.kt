package com.mustafaunlu.contactmanager.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafaunlu.contactmanager.common.ResponseStateHandle
import com.mustafaunlu.contactmanager.di.coroutine.IoDispatcher
import com.mustafaunlu.contactmanager.domain.entity.ContactEntity
import com.mustafaunlu.contactmanager.domain.usecase.read_contact.GetContactsUseCase
import com.mustafaunlu.contactmanager.domain.usecase.read_contact.GetSearchResultsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getContactsUseCase: GetContactsUseCase,
    private val getSearchResultsUseCase: GetSearchResultsUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {
    private val _lastTenContacts = MutableLiveData<ResponseStateHandle<List<ContactEntity>>>()
    val lastTenContacts: LiveData<ResponseStateHandle<List<ContactEntity>>> get() = _lastTenContacts

    private val _searchContact = MutableLiveData<ResponseStateHandle<List<ContactEntity>>>()
    val searchContact: LiveData<ResponseStateHandle<List<ContactEntity>>> get() = _searchContact

    fun getLastTenContacts() {
        viewModelScope.launch(ioDispatcher) {
            getContactsUseCase().collectLatest {
                when (it) {
                    is ResponseStateHandle.Success -> _lastTenContacts.postValue(ResponseStateHandle.Success(it.result))
                    is ResponseStateHandle.Error -> _lastTenContacts.postValue(ResponseStateHandle.Error(it.exception))
                    ResponseStateHandle.Loading -> _lastTenContacts.postValue(ResponseStateHandle.Loading)
                }
            }
        }
    }

    fun getSearchResults(search: String) {
        viewModelScope.launch(ioDispatcher) {
            getSearchResultsUseCase(search).collectLatest {
                when (it) {
                    is ResponseStateHandle.Success -> _searchContact.postValue(ResponseStateHandle.Success(it.result))
                    is ResponseStateHandle.Error -> _searchContact.postValue(ResponseStateHandle.Error(it.exception))
                    ResponseStateHandle.Loading -> _searchContact.postValue(ResponseStateHandle.Loading)
                }
            }
        }
    }
}
