package com.example.android.postscomments.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.android.postscomments.networking.Post


@Composable
fun PostsList(
    posts: List<Post>,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(
            items = posts,
            key = { it.postId }
        ) { post ->
            SinglePost(
                postTitle = post.title,
                postBody = post.body,
                backgroundColor = Color.Blue
            )

        }
    }
}
//TODO Implement color logic for the list items' background