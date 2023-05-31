package com.mustafaunlu.contactmanager.ui.family

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafaunlu.contactmanager.common.ResponseStateHandle
import com.mustafaunlu.contactmanager.di.coroutine.IoDispatcher
import com.mustafaunlu.contactmanager.domain.entity.ContactEntity
import com.mustafaunlu.contactmanager.domain.usecase.read_contact.GetContactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FamilyViewModel @Inject constructor(
    private val getContactsUseCase: GetContactsUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    private val _familyContacts = MutableLiveData<ResponseStateHandle<List<ContactEntity>>>()
    val familyContacts: LiveData<ResponseStateHandle<List<ContactEntity>>> = _familyContacts

    fun getFamilyContacts(group: String) {
        viewModelScope.launch(ioDispatcher) {
            getContactsUseCase.invoke(group).collectLatest {
                when (it) {
                    is ResponseStateHandle.Success -> _familyContacts.postValue(ResponseStateHandle.Success(it.result))
                    is ResponseStateHandle.Error -> _familyContacts.postValue(ResponseStateHandle.Error(it.exception))
                    ResponseStateHandle.Loading -> _familyContacts.postValue(ResponseStateHandle.Loading)
                }
            }
        }
    }
}
