package ru.netology.nmedia.repository

import androidx.lifecycle.map
import ru.netology.nmedia.convert.toEntity
import ru.netology.nmedia.convert.toModel
import ru.netology.nmedia.dao.PostDao
import ru.netology.nmedia.dto.Post

class PostRepositoryImpl(
    private val postDao: PostDao
) : PostRepository  {

    override val data = postDao.getAll().map { entities ->
        entities.map { it.toModel() }
    }

    override fun save(post: Post) {
        if (post.id == NEW_POST_ID) insert(post) else update(post)
    }

    override fun likeById(id: Long) {
        postDao.likeById(id)
    }

    override fun shareById(id: Long) {
        postDao.shareById(id)
    }

    override fun removeById(id: Long) {
        postDao.removeById(id)
    }

    private fun insert(post: Post) {
        postDao.insert(post.toEntity())
    }

    private fun update(post: Post) {
        postDao.update(post.toEntity())
    }

    private companion object {
        const val NEW_POST_ID = 0L
    }

}