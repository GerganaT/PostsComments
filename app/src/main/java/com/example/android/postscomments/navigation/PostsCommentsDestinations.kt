package com.example.android.postscomments.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

// contract,defining the minimum necessary attributes for each navigation destination
interface PostsCommentsScreens{
    val route:String
}

object PostsScreen:PostsCommentsScreens {
   override val route = "posts_screen"
}

object CommentsScreen:PostsCommentsScreens {
   override val route = "comments_screen"
}


object SingleScreen {

    val route = "single_screen"
    const val screenTypeArg = "screen_type"

    // the navigation" contract" that  has to be followed
    val routeWithArgs = "${route}/{${screenTypeArg}}"
    val arguments = listOf(
        navArgument(screenTypeArg) { type = NavType.IntType })
    val deepLinks = listOf(navDeepLink { uriPattern = "postscomments://$routeWithArgs" })
}

// the screen names in the TopAppBar
val applicationScreens = listOf(PostsScreen,CommentsScreen)