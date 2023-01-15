package com.example.android.postscomments.ui.comments

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.android.postscomments.R
import com.example.android.postscomments.ui.ui.theme.Dimens
import com.example.android.postscomments.ui.ui.theme.PostsCommentsTheme


@Composable
fun SingleComment(
    modifier: Modifier = Modifier,
    commentAndBubbleColor: Color,
    commentEmail: String,
    commentBody: String
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(Dimens.paddingStandard),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_chat_bubble_24),
            // no need for description as this is a purely decorative item
            contentDescription = "",
            tint = commentAndBubbleColor,
            modifier = Modifier
                .size(Dimens.iconSize)
                .padding(end = Dimens.paddingStandard)
        )
        Text(
            buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = commentAndBubbleColor,
                        fontWeight = FontWeight.Bold
                    )
                ) { append(commentEmail) }
                append(" ")
                append(commentBody)
            }

        )
    }

}

@Preview(showBackground = true)
@Composable
fun SingleCommentPreview() {
    PostsCommentsTheme {
        SingleComment(
            commentAndBubbleColor = Color.Cyan,
            commentEmail = "someone@somedomain.com",
            commentBody =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                    "Maecenas maximus dolor non felis elementum placerat. Vestibulum" +
                    "a sagittis nibh, non."
        )
    }
}