package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likedByMe: Boolean,
    val liked: Int,
    val sharedByMe: Boolean,
    val shared: Int,
    val viewed: Int,
    val video: String? = null
)
