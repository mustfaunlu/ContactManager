package com.mustafaunlu.contactmanager.ui.teammate

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
class TeammateViewModel @Inject constructor(
    private val getContactsUseCase: GetContactsUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    private val _teammateContacts = MutableLiveData<ResponseStateHandle<List<ContactEntity>>>()
    val teammateContacts: LiveData<ResponseStateHandle<List<ContactEntity>>> get() = _teammateContacts

    fun getTeammateContacts(group: String) {
        viewModelScope.launch(ioDispatcher) {
            getContactsUseCase.invoke(group).collectLatest {
                when (it) {
                    is ResponseStateHandle.Success -> _teammateContacts.postValue(ResponseStateHandle.Success(it.result))
                    is ResponseStateHandle.Error -> _teammateContacts.postValue(ResponseStateHandle.Error(it.exception))
                    ResponseStateHandle.Loading -> _teammateContacts.postValue(ResponseStateHandle.Loading)
                }
            }
        }
    }
}
