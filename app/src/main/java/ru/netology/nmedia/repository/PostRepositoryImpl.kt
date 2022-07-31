package ru.netology.nmedia.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import ru.netology.nmedia.dto.Post
import java.util.concurrent.TimeUnit

class PostRepositoryImpl: PostRepository {
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()
    private val gson = Gson()
    private val typeToken = object : TypeToken<List<Post>>() {}

    companion object {
        private const val BASE_URL = "http://10.0.2.2:9999"
        private val jsonType = "application/json".toMediaType()
    }

    override fun getAll(): List<Post> {
        val request: Request = Request.Builder()
            .url("${BASE_URL}/api/slow/posts")
            .build()

        return client.newCall(request)
            .execute()
            .let { it.body?.string() ?: throw RuntimeException("body is null") }
            .let {
                gson.fromJson(it, typeToken.type)
            }
    }

    override fun likeById(id: Long) {
        // TODO: do this in homework
    }

    override fun shareById(id: Long) {
        // TODO: do this in the future
    }

    override fun save(post: Post) {
        val request: Request = Request.Builder()
            .post(gson.toJson(post).toRequestBody(jsonType))
            .url("${BASE_URL}/api/slow/posts")
            .build()

        client.newCall(request)
            .execute()
            .close()
    }

    override fun removeById(id: Long) {
        val request: Request = Request.Builder()
            .delete()
            .url("${BASE_URL}/api/slow/posts/$id")
            .build()

        client.newCall(request)
            .execute()
            .close()
    }
}

//package ru.netology.nmedia.repository
//
//import androidx.lifecycle.Transformations
//import ru.netology.nmedia.convert.toEntity
//import ru.netology.nmedia.convert.toModel
//import ru.netology.nmedia.dao.PostDao
//import ru.netology.nmedia.dto.Post
//
//class PostRepositoryImpl(
//    private val postDao: PostDao
//) : PostRepository  {
//
//    override fun getAll() = Transformations.map(postDao.getAll()) { list ->
//        list.map {
//            it.toModel()
//        }
//    }
//
//    override fun save(post: Post) {
//        if (post.id == NEW_POST_ID) insert(post) else update(post)
//    }
//
//    override fun likeById(id: Long) {
//        postDao.likeById(id)
//    }
//
//    override fun shareById(id: Long) {
//        postDao.shareById(id)
//    }
//
//    override fun removeById(id: Long) {
//        postDao.removeById(id)
//    }
//
//    private fun insert(post: Post) {
//        postDao.insert(post.toEntity())
//    }
//
//    private fun update(post: Post) {
//        postDao.update(post.toEntity())
//    }
//
//    private companion object {
//        const val NEW_POST_ID = 0L
//    }
//
//}