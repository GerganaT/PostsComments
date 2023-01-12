package com.example.android.postscomments.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.postscomments.PostsCommentsRepository
import com.example.android.postscomments.networking.Post
import com.example.android.postscomments.networking.PostsCommentsApiServiceEntryPoint
import com.example.android.postscomments.ui.ui.theme.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostsCommentsViewModel : ViewModel() {
    private var mutablePostsList = MutableStateFlow<SnapshotStateList<Post>>(mutableStateListOf())

    val postsList: StateFlow<SnapshotStateList<Post>>
        get() = mutablePostsList


    private val repository = PostsCommentsRepository(PostsCommentsApiServiceEntryPoint.apiService)


    init {
        viewModelScope.launch {
            mutablePostsList.value = repository.getData().toMutableStateList()
        }
    }

    fun getColorPerAuthor(authorId: Int = 0): Color {
        val authorsAndColors: HashMap<Int, Color> = hashMapOf(
            1 to Peach200, 2 to Green200, 3 to Lime200, 4 to Pink200, 5 to Blue200,
            6 to Teal200, 7 to Fuchsia200, 8 to Purple500, 9 to Orange200, 10 to Tomato200
        )
        return authorsAndColors[authorId]!!
    }

}
//TODO fix dependency tight coupling here