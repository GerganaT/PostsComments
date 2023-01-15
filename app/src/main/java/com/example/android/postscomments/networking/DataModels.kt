package com.example.android.postscomments.networking

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// Classes ,used by Moshi, to model the network data into Kotlin objects
// I realize that I could've used Scalars converter but I really like the possibility
// to be able to define custom variable names,while still matching the original JSON fields' names

data class Post(
    @Json(name = "userId") val authorId: Int,
    @Json(name = "id") val postId: Int,
    val title: String,
    val body: String
)

data class Comment(
    @Json(name = "id") val commentId:Int,
    @Json(name = "email") val userEmail: String,
    val body: String
)