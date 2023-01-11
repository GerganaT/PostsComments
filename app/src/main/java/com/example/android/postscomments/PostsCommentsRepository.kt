package com.example.android.postscomments

import android.util.Log
import com.example.android.postscomments.networking.Post
import com.example.android.postscomments.networking.PostsCommentsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//normally I'd use some DI framework here but for simplicity I'm skipping that
class PostsCommentsRepository(
    val postsApi: PostsCommentsApiService
) {


    suspend fun getData(): List<Post> {
        var posts:List<Post> = listOf()
        withContext(Dispatchers.IO) {
            val response = postsApi.getAllPosts()
            if(response.isSuccessful){
                 posts = response.body()!!

            }

        }
        return posts
    }
}
//TODO add Result error handling/ show toast