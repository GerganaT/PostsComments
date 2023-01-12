package com.example.android.postscomments.repo

/**Result class to encapsulate the different stages of data when retrieved from the Web*/

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Progress<out T : Any>(val isLoading: Boolean = true) : Result<T>()
    data class Error(val showErrorMessage: Boolean = true) : Result<Nothing>()
}
