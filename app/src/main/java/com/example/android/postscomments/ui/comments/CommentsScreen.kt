package com.example.android.postscomments.ui.comments

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.android.postscomments.R
import com.example.android.postscomments.networking.Comment
import com.example.android.postscomments.repo.Result
import com.example.android.postscomments.ui.PostsCommentsViewModel
import com.example.android.postscomments.ui.ShowLoadingProgressIndicator
import com.example.android.postscomments.ui.ui.theme.Dimens
import com.example.android.postscomments.ui.ui.theme.PostsCommentsTheme
import kotlinx.coroutines.launch

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
            val randomColorPerComment = { viewModel.getRandomColorPerComment() }
            CommentsList(comments = comments, commentAndBubbleColor = randomColorPerComment)
        }
        Result.Error -> {
            NoDataDisplayedAlertDialog {

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
    modifier:Modifier = Modifier,
    onDismissClicked:() -> Unit

){
    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            TextButton(onClick = onDismissClicked)
            { Text(text = stringResource(R.string.alert_dialog_dismiss_button)) }
        },
        title = { Text(text = stringResource(R.string.alert_dialog_text)) }
    )
}

@Preview
@Composable
fun NoDataDisplayedAlertDialogPreview(){
    PostsCommentsTheme {
        NoDataDisplayedAlertDialog {

        }
    }
}

