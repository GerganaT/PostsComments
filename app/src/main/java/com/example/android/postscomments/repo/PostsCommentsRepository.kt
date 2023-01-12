package com.example.android.postscomments.repo

import com.example.android.postscomments.networking.Post
import com.example.android.postscomments.networking.PostsCommentsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//normally I'd use some DI framework here but for simplicity I'm skipping that
class PostsCommentsRepository(
    val postsApi: PostsCommentsApiService
) {


    suspend fun getData(): Result<List<Post>> {

        var result: Result<List<Post>>
        withContext(Dispatchers.IO) {
            val response = postsApi.getAllPosts()
            result = when {
                response.body()?.isEmpty() == true -> Result.Progress(true)
                response.isSuccessful -> Result.Success(response.body()!!)
                else -> Result.Error(true)
            }
        }
        return result
    }
}
//TODO add Result error handling/ show toast