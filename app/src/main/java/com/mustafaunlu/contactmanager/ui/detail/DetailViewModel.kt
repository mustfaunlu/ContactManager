package com.mustafaunlu.contactmanager.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafaunlu.contactmanager.common.ResponseStateHandle
import com.mustafaunlu.contactmanager.di.coroutine.IoDispatcher
import com.mustafaunlu.contactmanager.domain.entity.ContactEntity
import com.mustafaunlu.contactmanager.domain.usecase.delete_contact.DeleteContactUseCase
import com.mustafaunlu.contactmanager.domain.usecase.read_contact.GetContactsUseCase
import com.mustafaunlu.contactmanager.domain.usecase.update_contact.UpdateContactUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getContactsUseCase: GetContactsUseCase,
    private val deleteContactUseCase: DeleteContactUseCase,
    private val updateContactUseCase: UpdateContactUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {
    private val _contact = MutableLiveData<ResponseStateHandle<ContactEntity>>()
    val contact: LiveData<ResponseStateHandle<ContactEntity>> get() = _contact

    fun getSingleContact(id: Int) {
        viewModelScope.launch(ioDispatcher) {
            getContactsUseCase.invoke(id).collectLatest {
                when (it) {
                    is ResponseStateHandle.Success -> _contact.postValue(ResponseStateHandle.Success(it.result))
                    is ResponseStateHandle.Error -> _contact.postValue(ResponseStateHandle.Error(it.exception))
                    ResponseStateHandle.Loading -> _contact.postValue(ResponseStateHandle.Loading)
                }
            }
        }
    }

    fun deleteContact(contact: ContactEntity) {
        viewModelScope.launch(ioDispatcher) {
            deleteContactUseCase.invoke(contact)
        }
    }

    fun updateContact(contact: ContactEntity) {
        viewModelScope.launch(ioDispatcher) {
            updateContactUseCase.invoke(contact)
        }
    }
}
