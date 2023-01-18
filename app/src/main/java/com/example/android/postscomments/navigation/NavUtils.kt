package com.example.android.postscomments.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.android.postscomments.ui.PostsCommentsViewModel
import com.example.android.postscomments.ui.PostsListScreen
import com.example.android.postscomments.ui.comments.CommentsListScreen


//Util functions to allow navigation between composable screens


@Composable
fun PostsCommentsNavHost(
    navController: NavHostController,
    viewModel: PostsCommentsViewModel,
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    appBarTitle: String?,
) {

    NavHost(
        navController = navController,
        startDestination = PostsScreen.route
    ) {
        composable(PostsScreen.route) {
            PostsListScreen(viewModel = viewModel,
                onSinglePostClicked = { postId: Int ->
                    navController.navigateToPostComments(postId)
                })
            viewModel.resetCommentsDataStatusToLoading()
        }
        composable(
            route = SingleScreen.routeWithArgs,
            arguments = SingleScreen.arguments,
            deepLinks = SingleScreen.deepLinks
        ) { navBackStackEntry ->
            // Retrieve the passed argument
            val postId =
                navBackStackEntry.arguments?.getInt(SingleScreen.postIdArg)
            // Pass postId to CommentsScreen
            CommentsListScreen(
                onDismissClicked = { navController.navigateSingleTopTo(PostsScreen.route) },
                viewModel = viewModel,
                postId = postId!!,
                appBarTitle = appBarTitle
            )
        }
    }


}

private fun NavHostController.navigateSingleTopTo(route: String) =
    navigate(route) {
        popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) {
        }
        // use launch single top too prevent multiple copies of a destination in the backstack
        launchSingleTop = true
    }

fun NavHostController.navigateToPostComments(postId: Int) {
    navigateSingleTopTo("${SingleScreen.route}/$postId")
}