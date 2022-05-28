package ru.netology.nmedia.post

data class Post(
   val id: Long,
   val author: String,
   val content: String,
   val published: String,
   val likedByMe: Boolean,
   val liked: Int,
   val shared: Int,
   val viewed: Int
)
