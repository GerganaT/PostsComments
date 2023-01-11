package com.example.android.postscomments.ui

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.postscomments.PostsCommentsRepository
import com.example.android.postscomments.networking.Post
import com.example.android.postscomments.networking.PostsCommentsApiServiceEntryPoint
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PostsCommentsViewModel:ViewModel() {
    private var mutablePostsList= MutableStateFlow<SnapshotStateList<Post>>(mutableStateListOf())

    val postsList:StateFlow<SnapshotStateList<Post>>
    get() = mutablePostsList


    private val repository = PostsCommentsRepository(PostsCommentsApiServiceEntryPoint.apiService)


 init {
     viewModelScope.launch {
         mutablePostsList.value = repository.getData().toMutableStateList()
     }
 }



}
//TODO fix dependency tight coupling here