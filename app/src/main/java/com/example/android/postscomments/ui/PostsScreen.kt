package com.example.android.postscomments.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle


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
                backgroundColor = viewModel.getColorPerAuthor(post.authorId)
            )

        }
    }
}

