package ru.netology.nmedia.repository

import ru.netology.nmedia.dto.Post
import androidx.lifecycle.LiveData

interface PostRepository {
//    fun getAll(): LiveData<List<Post>>
    val data: LiveData<List<Post>>
    fun likeById(id: Long)
    fun shareById(id: Long)
    fun removeById(id: Long)
    fun save(post: Post)
}