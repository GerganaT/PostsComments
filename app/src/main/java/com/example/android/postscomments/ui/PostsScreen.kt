package com.example.android.postscomments.ui

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.android.postscomments.networking.Post
import kotlin.coroutines.coroutineContext


@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun PostsList(
    modifier: Modifier = Modifier,
    viewModel: PostsCommentsViewModel
) {
    val posts by
    viewModel.postsList.collectAsStateWithLifecycle()


    LazyColumn {
        items(
            items = posts,
            key = { it.postId }
        ) { post ->
            SinglePost(
                postTitle = post.title,
                postBody = post.body,
                backgroundColor = Color.LightGray
            )

        }
    }
}
//TODO Implement color logic for the list items' background
