package com.example.android.postscomments.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.android.postscomments.R
import com.example.android.postscomments.navigation.PostsCommentsNavHost
import com.example.android.postscomments.navigation.PostsScreen
import com.example.android.postscomments.ui.ui.theme.Dimens
import com.example.android.postscomments.ui.ui.theme.PostsCommentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: PostsCommentsViewModel by viewModels()

        setContent {
            val navController = rememberNavController()
            var screenTitle by rememberSaveable {
                mutableStateOf("")
            }

            PostsCommentsTheme {
                // swap screen labels whenever navigating between both screens
                LaunchedEffect(navController) {
                    navController.currentBackStackEntryFlow.collect { backStackEntry ->
                        title = backStackEntry.destination.route.toString()
                        screenTitle = if (title == PostsScreen.route) {
                            getString(R.string.posts_screen_label)
                        } else {
                            getString(R.string.comments_screen_label)
                        }
                    }
                }

                PostsCommentsMainScreen(
                    appBarTitle = screenTitle,
                    navigateBack = { navController.navigateUp() },
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }

    @Composable
    fun PostsCommentsMainScreen(
        modifier: Modifier = Modifier,
        appBarTitle: String?,
        navigateBack: () -> Unit,
        navController: NavHostController,
        viewModel: PostsCommentsViewModel

    ) {
        Scaffold(
            topBar = {
                showTopAppBar(
                    screenTitle = appBarTitle,
                    navigateBack = navigateBack
                )
            },
            content = { padding: PaddingValues ->
                PostsCommentsNavHost(
                    navController = navController,
                    viewModel = viewModel,
                    padding = padding
                )
            }
        )

    }

    @Composable
    fun showTopAppBar(
        modifier: Modifier = Modifier,
        screenTitle: String?,
        navigateBack: () -> Unit
    ) {
        if (screenTitle == stringResource(id = R.string.comments_screen_label)) {
            TopAppBar(
                title = { Text(text = screenTitle) },
                navigationIcon = {
                    IconButton(onClick = {
                        navigateBack()
                    }) {
                        Icon(
                            Icons.Filled
                                .ArrowBack,
                            stringResource(R.string.back_icon_app_bar_content_description)
                        )


                    }
                },
                elevation = Dimens.appBarElevation
            )


        } else {
            TopAppBar(
                title = { Text(text = screenTitle ?: "") }, elevation = Dimens.appBarElevation
            )

        }
    }
}

