package com.example.android.postscomments.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.android.postscomments.R
import com.example.android.postscomments.networking.Post

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun PostsListScreen(
    modifier: Modifier = Modifier,
    viewModel: PostsCommentsViewModel
) {
    val posts by
    viewModel.postsList.collectAsStateWithLifecycle()
    val getBackgroundColor = { authorId: Int -> viewModel.getColorPerAuthor(authorId) }
    if (posts.isEmpty()) {
        ShowLoadingProgressIndicator()
    } else {
        PostsList(posts = posts, getBackgroundColor = getBackgroundColor)
    }
//TODO showing progress indicator works but try with result as we need to show an error message too
}

@Composable
fun PostsList(
    modifier: Modifier = Modifier,
    posts: List<Post>,
    getBackgroundColor: (Int) -> Color

) {

    LazyColumn {
        items(
            items = posts,
            key = { it.postId }
        ) { post ->
            SinglePost(
                postTitle = post.title,
                postBody = post.body,
                backgroundColor = getBackgroundColor(post.authorId)
            )

        }
    }
}

@Composable
fun ShowLoadingProgressIndicator(
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    )
}

//TODO fix text centering here
@Composable
fun ShowErrorWhenDataNotLoaded(
    modifier: Modifier = Modifier,
    onTextClicked: (Int) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
    ) {
        Text(
            text = stringResource(id = R.string.data_not_loaded_error_message_1st_part),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Text(
            text = stringResource(id = R.string.data_not_loaded_error_message_2nd_part),
            textAlign = TextAlign.Center

        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShowErrorWhenDataNotLoadedPreview() {
    ShowErrorWhenDataNotLoaded { }
}
