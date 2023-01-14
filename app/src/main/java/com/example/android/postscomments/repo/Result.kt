package com.example.android.postscomments.repo

import com.example.android.postscomments.networking.Post

/**Result class to encapsulate the different stages of data when retrieved from the Web*/

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Progress<out T : Any>(val progressData:List<Post> = emptyList()) : Result<T>()
    object Error : Result<Nothing>()
}
