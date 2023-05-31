package com.mustafaunlu.contactmanager.common

sealed class ResponseStateHandle<out T : Any> {
    object Loading : ResponseStateHandle<Nothing>()
    data class Success<out T : Any>(val result: T) : ResponseStateHandle<T>()
    data class Error(val exception: Exception) : ResponseStateHandle<Nothing>()
}