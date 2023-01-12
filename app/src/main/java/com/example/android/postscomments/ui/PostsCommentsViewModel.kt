package com.example.android.postscomments.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
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
import kotlinx.coroutines.launch

class PostsCommentsViewModel : ViewModel() {
    private var mutablePostsList = MutableStateFlow<SnapshotStateList<Post>>(mutableStateListOf())

    val postsList: StateFlow<SnapshotStateList<Post>>
        get() = mutablePostsList

    private var _isError = mutableStateOf(false)
    val isError: State<Boolean>
        get() = _isError

    private var _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean>
        get() = _isLoading

    private val repository = PostsCommentsRepository(PostsCommentsApiServiceEntryPoint.apiService)


    init {
        viewModelScope.launch {
            getPostsData()
        }
    }

    fun getColorPerAuthor(authorId: Int = 0): Color {
        val authorsAndColors: HashMap<Int, Color> = hashMapOf(
            1 to Peach200, 2 to Green200, 3 to Lime200, 4 to Pink200, 5 to Blue200,
            6 to Teal200, 7 to Fuchsia200, 8 to Purple500, 9 to Orange200, 10 to Tomato200
        )
        return authorsAndColors[authorId]!!
    }

    suspend fun getPostsData() {
        val postsData = repository.getData()
        when (postsData) {
            is Result.Progress -> _isLoading.value = postsData.isLoading
            is Result.Success -> mutablePostsList.value = postsData.data.toMutableStateList()
            is Result.Error -> _isError.value = postsData.showErrorMessage
        }
    }

}
//TODO fix dependency tight coupling here