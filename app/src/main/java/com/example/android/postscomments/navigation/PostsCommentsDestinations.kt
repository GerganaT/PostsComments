package com.example.android.postscomments.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

// contract,defining the minimum necessary attributes for each navigation destination
interface PostsCommentsScreens {
    val route: String
}

object PostsScreen : PostsCommentsScreens {
    override val route = "posts_screen"
}

object CommentsScreen : PostsCommentsScreens {
    override val route = "comments_screen"
}


object SingleScreen : PostsCommentsScreens {

    override val route = "comments_screen"
    const val postIdArg = "postId"

    // the navigation" contract" that  has to be followed so data can be passed through screens
    val routeWithArgs = "${route}/{${postIdArg}}"
    val arguments = listOf(
        navArgument(postIdArg) { type = NavType.IntType })
    val deepLinks = listOf(navDeepLink { uriPattern = "postscomments://$routeWithArgs" })
}


