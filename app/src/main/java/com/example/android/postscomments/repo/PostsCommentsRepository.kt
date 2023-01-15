package com.example.android.postscomments.repo

import com.example.android.postscomments.networking.Comment
import com.example.android.postscomments.networking.Post
import com.example.android.postscomments.networking.PostsCommentsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//normally I'd use some DI framework here but for simplicity I'm skipping that
class PostsCommentsRepository(
    val postsApi: PostsCommentsApiService
) {


    suspend fun getPostsData(): Result<List<Post>> {
        return try {
            withContext(Dispatchers.IO) {
                val response = postsApi.getAllPosts()
                when {
                    response.body()?.isEmpty() == true -> Result.Progress()
                    response.isSuccessful -> Result.Success(response.body()!!)
                    else -> Result.Error
                }
            }
        }
        // catch any exception be it network or other issue - related
        catch (exception: Exception) {
            Result.Error
        }
    }
//TODO  - navigation with deep linking,click on each post and go to relevant comment per post id
    //TODO to get posts' comments

    suspend fun getCommentsData(postId:Int): Result<List<Comment>> {
        return try {
            withContext(Dispatchers.IO) {
                val response = postsApi.getPostRelatedComments(postId)
                when {
                    response.body()?.isEmpty() == true -> Result.Progress()
                    response.isSuccessful -> Result.Success(response.body()!!)
                    else -> Result.Error
                }
            }
        }
        // catch any exception be it network or other issue - related
        catch (exception: Exception) {
            Result.Error
        }
    }
}
