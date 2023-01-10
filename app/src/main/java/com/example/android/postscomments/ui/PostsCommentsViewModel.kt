package com.example.android.postscomments.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.postscomments.PostsCommentsRepository
import com.example.android.postscomments.networking.Post
import com.example.android.postscomments.networking.PostsCommentsApiServiceEntryPoint
import kotlinx.coroutines.launch

class PostsCommentsViewModel:ViewModel() {
    val postsList: State<List<Post>>
    get() = data
    val repository = PostsCommentsRepository(PostsCommentsApiServiceEntryPoint.apiService)

 private var data:MutableState<List<Post>> = mutableStateOf(listOf())
 init {
     viewModelScope.launch {
         data = mutableStateOf(repository.getData())
     }
 }

}
//TODO fix dependency tight coupling here