package com.example.android.postscomments.ui.comments

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.android.postscomments.networking.Comment
import com.example.android.postscomments.repo.Result
import com.example.android.postscomments.ui.PostsCommentsViewModel
import com.example.android.postscomments.ui.ShowLoadingProgressIndicator

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun CommentsListScreen(
    modifier: Modifier = Modifier,
    viewModel: PostsCommentsViewModel
) {
    val result by viewModel.commentsDataFetchResult.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()
    when (result) {
        is Result.Progress -> ShowLoadingProgressIndicator()
        is Result.Success -> {
            val comments by
            viewModel.commentList.collectAsStateWithLifecycle()
            val randomColorPerComment = {viewModel.getRandomColorPerComment()}
            CommentsList(comments = comments, commentAndBubbleColor = randomColorPerComment)
        }
        Result.Error -> {
            Text("Not cool")
            //TODO show the alert box to be clicked upon
        }


    }
}


@Composable
fun CommentsList(
    modifier: Modifier = Modifier,
    comments: List<Comment>,
    commentAndBubbleColor: () -> Color
) {
    LazyColumn {
        items(
            items = comments,
            key = { it.commentId }
        ) { comment ->
            SingleComment(
                commentAndBubbleColor = commentAndBubbleColor(),
                commentEmail = comment.userEmail,
                commentBody = comment.body,

                )

        }
    }
}