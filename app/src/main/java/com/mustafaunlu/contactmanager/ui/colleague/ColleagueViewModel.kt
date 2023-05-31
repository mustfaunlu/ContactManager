package com.mustafaunlu.contactmanager.ui.colleague

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
class ColleagueViewModel @Inject constructor(
    private val getContactsUseCase: GetContactsUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    private val _colleagueContacts = MutableLiveData<ResponseStateHandle<List<ContactEntity>>>()
    val colleagueContacts: LiveData<ResponseStateHandle<List<ContactEntity>>> = _colleagueContacts

    fun getColleagueContact(group: String) {
        viewModelScope.launch(ioDispatcher) {
            getContactsUseCase.invoke(group).collectLatest {
                when (it) {
                    is ResponseStateHandle.Success -> _colleagueContacts.postValue(ResponseStateHandle.Success(it.result))
                    is ResponseStateHandle.Error -> _colleagueContacts.postValue(ResponseStateHandle.Error(it.exception))
                    ResponseStateHandle.Loading -> _colleagueContacts.postValue(ResponseStateHandle.Loading)
                }
            }
        }
    }
}
