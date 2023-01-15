package com.example.android.postscomments.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.postscomments.networking.Comment
import com.example.android.postscomments.networking.Post
import com.example.android.postscomments.networking.PostsCommentsApiServiceEntryPoint
import com.example.android.postscomments.repo.PostsCommentsRepository
import com.example.android.postscomments.repo.Result
import com.example.android.postscomments.ui.ui.theme.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
/** ViewModel to shape the data for the UI for the Posts and Comments screens.
 * Ideally, there could be 2 ViewModels to support each screen,however,given the similar logic
 * and the relatively short code and for simplicity,I have used one combined ViewModel*/
class PostsCommentsViewModel : ViewModel() {

    private val repository =
        PostsCommentsRepository(PostsCommentsApiServiceEntryPoint.apiService)

    // The logic for the Posts screen

    private var _postsList =
        MutableStateFlow<SnapshotStateList<Post>>(mutableStateListOf())

    val postsList: StateFlow<SnapshotStateList<Post>> =
        _postsList.asStateFlow()

    private var _postsDataFetchResult: MutableStateFlow<Result<List<Post>>> =
        MutableStateFlow(Result.Progress())

    val postsDataFetchResult: StateFlow<Result<List<Post>>> =
        _postsDataFetchResult.asStateFlow()

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
        val postsResult = repository.getPostsData()
        _postsDataFetchResult.value = postsResult
        if (postsResult is Result.Success) {
            _postsList.value =
                postsResult.data.sortedBy { post: Post -> post.title }.toMutableStateList()
        }
    }

    fun resetDataStatusToLoading(){
        _postsDataFetchResult.value = Result.Progress()
    }

    //The logic for the comments screen

    private var _commentList =
        MutableStateFlow<SnapshotStateList<Comment>>(mutableStateListOf())

    val commentList: StateFlow<SnapshotStateList<Comment>> =
        _commentList.asStateFlow()

    private var _commentsDataFetchResult: MutableStateFlow<Result<List<Comment>>> =
        MutableStateFlow(Result.Progress())

    val commentsDataFetchResult: StateFlow<Result<List<Comment>>> =
        _commentsDataFetchResult.asStateFlow()


    suspend fun getCommentsDataAndRecordResult(postId:Int) {
        val commentsResult = repository.getCommentsData(postId)
        _commentsDataFetchResult.value = commentsResult
        if (commentsResult is Result.Success) {
            _commentList.value =
                commentsResult.data.toMutableStateList()
        }
    }

    fun resetCommentsDataStatusToLoading(){
        _commentsDataFetchResult.value = Result.Progress()
    }


    fun getRandomColorPerComment(): Color {
        val colorsWithIds: HashMap<Int, Color> = hashMapOf(
            1 to Peach200, 2 to Green200, 3 to Lime200, 4 to Pink200, 5 to Blue200,
            6 to Teal200, 7 to Fuchsia200, 8 to Purple500, 9 to Orange200, 10 to Tomato200
        )
        val randomizedKey = colorsWithIds.keys.shuffled().last()
        return colorsWithIds[randomizedKey]!!
    }


}

// TODO fix dependency tight coupling here