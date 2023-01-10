package com.example.android.postscomments.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.lifecycleScope
import com.example.android.postscomments.PostsCommentsRepository
import com.example.android.postscomments.networking.Post
import com.example.android.postscomments.networking.PostsCommentsApiServiceEntryPoint
import com.example.android.postscomments.ui.ui.theme.PostsCommentsTheme
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val viewModel:PostsCommentsViewModel by viewModels()

        setContent {
                val rememberedData by rememberSaveable {
                    viewModel.postsList
                }
                PostsCommentsTheme {
                    PostsList(posts = rememberedData )
                    Log.i("com","compose called")
                }


        }
    }



        }





//TODO fix issue so you can show list
//TODO set up custom screen names Posts and Comments