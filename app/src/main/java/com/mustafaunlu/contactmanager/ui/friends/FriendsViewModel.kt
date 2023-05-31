package com.mustafaunlu.contactmanager.ui.friends

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
class FriendsViewModel @Inject constructor(
    private val getContactsUseCase: GetContactsUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    private val _friendsContacts = MutableLiveData<ResponseStateHandle<List<ContactEntity>>>()
    val friendsContacts: LiveData<ResponseStateHandle<List<ContactEntity>>> = _friendsContacts

    fun getFriendsContacts(group: String) {
        viewModelScope.launch(ioDispatcher) {
            getContactsUseCase.invoke(group).collectLatest {
                when (it) {
                    is ResponseStateHandle.Success -> _friendsContacts.postValue(ResponseStateHandle.Success(it.result))
                    is ResponseStateHandle.Error -> _friendsContacts.postValue(ResponseStateHandle.Error(it.exception))
                    ResponseStateHandle.Loading -> _friendsContacts.postValue(ResponseStateHandle.Loading)
                }
            }
        }
    }
}
