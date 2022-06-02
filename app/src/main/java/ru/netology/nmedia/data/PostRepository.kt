package ru.netology.nmedia.data

import ru.netology.nmedia.post.*
import androidx.lifecycle.LiveData

interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
    fun share()
}