package com.example.android.postscomments.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.android.postscomments.R
import com.example.android.postscomments.networking.Post
import com.example.android.postscomments.repo.Result
import com.example.android.postscomments.ui.ui.theme.Typography
import kotlinx.coroutines.launch


@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun PostsListScreen(
    modifier: Modifier = Modifier,
    viewModel: PostsCommentsViewModel
) {
    val result by viewModel.dataFetchResult.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()
    when (result) {
        is Result.Progress -> ShowLoadingProgressIndicator()
        is Result.Success -> {
            val posts by
            viewModel.postsList.collectAsStateWithLifecycle()
            val getBackgroundColor = { authorId: Int -> viewModel.getColorPerAuthor(authorId) }
            PostsList(posts = posts, getBackgroundColor = getBackgroundColor)
        }
        Result.Error -> {
            ShowErrorWhenDataNotLoaded {
                scope.launch {
                    viewModel.resetDataStatusToLoading()
                    viewModel.getPostsDataAndRecordResult()
                }
            }
        }


    }
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

@Composable
fun ShowErrorWhenDataNotLoaded(
    modifier: Modifier = Modifier,
    onTextClicked: () -> Unit
) {
    Box(modifier = modifier
        .fillMaxSize()
        .clickable { onTextClicked() })
    {
        Text(
            text = stringResource(id = R.string.data_not_loaded_error_message),
            style = Typography.h1,
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center
        )
    }


}

@Preview(showBackground = true)
@Composable
fun ShowErrorWhenDataNotLoadedPreview() {
    ShowErrorWhenDataNotLoaded {}
}
