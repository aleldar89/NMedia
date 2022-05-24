package ru.netology.nmedia

data class Post(
   val id: Long,
   val author: String,
   val content: String,
   val published: String,
   var likedByMe: Boolean = false,
   var liked: Int = 0,
   var shared: Int = 0
)
