package com.example.android.postscomments.ui.comments

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.android.postscomments.R
import com.example.android.postscomments.networking.Comment
import com.example.android.postscomments.repo.Result
import com.example.android.postscomments.ui.PostsCommentsViewModel
import com.example.android.postscomments.ui.ShowLoadingProgressIndicator
import com.example.android.postscomments.ui.ui.theme.Dimens
import com.example.android.postscomments.ui.ui.theme.PostsCommentsTheme
import kotlinx.coroutines.delay

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun CommentsListScreen(
    modifier: Modifier = Modifier,
    onDismissClicked: () -> Unit,
    viewModel: PostsCommentsViewModel,
    postId: Int
) {
    val result by viewModel.commentsDataFetchResult.collectAsStateWithLifecycle()
    // this prevents multiple recompositions whenever this screen is being navigated to
    LaunchedEffect(Unit) {
        // this delay is not really needed, but demonstrates that loading indicator is up every time
        // we navigate to comments screen ,as due to fast internet indicator is not there each time
        delay(2000)
        viewModel.getCommentsDataAndRecordResult(postId)
    }
    when (result) {
        is Result.Progress -> ShowLoadingProgressIndicator()
        is Result.Success -> {
            val comments by
            viewModel.commentList.collectAsStateWithLifecycle()
            val randomColorPerComment = { viewModel.getRandomColorPerComment() }
            CommentsList(comments = comments, commentAndBubbleColor = randomColorPerComment)

        }
        Result.Error -> {
            NoDataDisplayedAlertDialog {
                onDismissClicked()
            }


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
        itemsIndexed(
            items = comments,
            key = { _, comment: Comment -> comment.commentId }
        ) { commentIndex: Int, comment: Comment ->
            SingleComment(
                commentAndBubbleColor = commentAndBubbleColor(),
                commentEmail = comment.userEmail,
                commentBody = comment.body,
            )
            if (commentIndex < comments.lastIndex) {
                Divider(startIndent = Dimens.listIndent)
            }


        }

    }
}

@Composable
fun NoDataDisplayedAlertDialog(
    modifier: Modifier = Modifier,
    onDismissClicked: () -> Unit

) {
    AlertDialog(
        onDismissRequest = { },
        confirmButton = {},
        dismissButton =
        {
            TextButton(onClick = onDismissClicked)
            { Text(text = stringResource(R.string.alert_dialog_dismiss_button)) }
        },
        title = {
            Text(text = stringResource(R.string.alert_dialog_text))
        }
    )
}


@Preview
@Composable
fun NoDataDisplayedAlertDialogPreview() {
    PostsCommentsTheme {
        NoDataDisplayedAlertDialog {

        }
    }
}

