package com.example.android.postscomments.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.android.postscomments.ui.comments.CommentsListScreen
import com.example.android.postscomments.ui.ui.theme.PostsCommentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: PostsCommentsViewModel by viewModels()
        setContent {
            PostsCommentsTheme {
              PostsListScreen(viewModel = viewModel)


            }
        }
    }


}
//TODO set up custom screen names Posts and Comments and navigation
