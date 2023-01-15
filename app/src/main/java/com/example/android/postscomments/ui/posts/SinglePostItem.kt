package com.example.android.postscomments.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.android.postscomments.ui.ui.theme.Dimens
import com.example.android.postscomments.ui.ui.theme.Lime200
import com.example.android.postscomments.ui.ui.theme.PostsCommentsTheme


@Composable
fun SinglePost(
    postTitle: String,
    postBody: String,
    backgroundColor: Color,
    openRelatedComments: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .clickable { openRelatedComments() },
        color = backgroundColor
    ) {
        Column(
            modifier = modifier
                .padding(Dimens.paddingStandard)
        ) {
            Text(
                text = postTitle,
                modifier = Modifier.padding(bottom = Dimens.paddingSmall),
                fontWeight = FontWeight.Bold
            )
            Text(text = postBody)
        }
    }

}

@Preview
@Composable
fun SinglePostPreview() {
    PostsCommentsTheme {
        SinglePost(
            postTitle = "SomeExampleTitle",
            postBody =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                    " Maecenas maximus dolor non felis elementum placerat. Vestibulum " +
                    "a sagittis nibh, non.",
            backgroundColor = Lime200,
            openRelatedComments = {}
        )
    }

}