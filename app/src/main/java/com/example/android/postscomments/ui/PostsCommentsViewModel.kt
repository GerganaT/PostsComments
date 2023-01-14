package com.example.android.postscomments.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.postscomments.networking.Post
import com.example.android.postscomments.networking.PostsCommentsApiServiceEntryPoint
import com.example.android.postscomments.repo.PostsCommentsRepository
import com.example.android.postscomments.repo.Result
import com.example.android.postscomments.ui.ui.theme.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostsCommentsViewModel : ViewModel() {
    private var _postsList =
        MutableStateFlow<SnapshotStateList<Post>>(mutableStateListOf())

    val postsList: StateFlow<SnapshotStateList<Post>> =
        _postsList.asStateFlow()

    private var _dataFetchResult: MutableStateFlow<Result<List<Post>>> =
        MutableStateFlow(Result.Progress())

    val dataFetchResult: StateFlow<Result<List<Post>>> =
        _dataFetchResult.asStateFlow()

    private val repository =
        PostsCommentsRepository(PostsCommentsApiServiceEntryPoint.apiService)


    init {
        viewModelScope.launch {
            getPostsDataAndRecordResult()
        }
    }

    fun getColorPerAuthor(authorId: Int = 0): Color {
        val authorsAndColors: HashMap<Int, Color> = hashMapOf(
            1 to Peach200, 2 to Green200, 3 to Lime200, 4 to Pink200, 5 to Blue200,
            6 to Teal200, 7 to Fuchsia200, 8 to Purple500, 9 to Orange200, 10 to Tomato200
        )
        return authorsAndColors[authorId]!!
    }

    suspend fun getPostsDataAndRecordResult() {
        val postsResult = repository.getData()
        _dataFetchResult.value = postsResult
        if (postsResult is Result.Success) {
            _postsList.value =
                postsResult.data.sortedBy { post: Post -> post.title }.toMutableStateList()
        }
    }

    fun resetDataStatusToLoading(){
        _dataFetchResult.value = Result.Progress()
    }

}

//TODO fix dependency tight coupling here