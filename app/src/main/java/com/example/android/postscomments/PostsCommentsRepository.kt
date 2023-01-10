package com.example.android.postscomments

import com.example.android.postscomments.networking.Post
import com.example.android.postscomments.networking.PostsCommentsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//normally I'd use some DI framework here but for simplicity I'm skipping that
class PostsCommentsRepository(
    val postsApi: PostsCommentsApiService
) {

    var posts: List<Post> = listOf()
    suspend fun getData(): List<Post> {
        withContext(Dispatchers.IO) {
            posts = postsApi.getAllPosts()
        }
        return posts
    }
}