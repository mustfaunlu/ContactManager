package com.mustafaunlu.contactmanager.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafaunlu.contactmanager.di.coroutine.IoDispatcher
import com.mustafaunlu.contactmanager.domain.entity.ContactEntity
import com.mustafaunlu.contactmanager.domain.usecase.create_contact.InsertContactUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateContactViewModel @Inject constructor(
    private val insertContactUseCase: InsertContactUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    fun insertContact(newContact: ContactEntity) {
        viewModelScope.launch(ioDispatcher) {
            insertContactUseCase(newContact)
        }
    }
}
