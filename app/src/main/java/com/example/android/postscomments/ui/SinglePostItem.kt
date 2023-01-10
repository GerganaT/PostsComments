package com.example.android.postscomments.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android.postscomments.ui.ui.theme.Lime200
import com.example.android.postscomments.ui.ui.theme.PostsCommentsTheme


@Composable
fun SinglePost(
    postTitle: String,
    postBody: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Surface(color = backgroundColor) {
        Column(
            modifier = modifier
                .padding(8.dp)
        ) {
            Text(
                text = postTitle,
                modifier = Modifier.padding(bottom = 4.dp),
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
            backgroundColor = Lime200
        )
    }

}