package ru.netology.nmedia.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PostRepositoryFileImpl(
    private val context: Context
) : PostRepository  {
    private val postFileName = "posts.json"
    private val gson = Gson()
    private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type

    private var posts = readAll()
        set(value) {
            field = value
            sync()
        }

    private fun sync() {
        context.openFileOutput(postFileName, Context.MODE_PRIVATE).bufferedWriter().use {
            it.write(gson.toJson(posts, type))
        }
    }

    private fun readAll(): List<Post> {
        val file = context.filesDir.resolve(postFileName)
        return if (file.exists()) {
            context.openFileInput(postFileName).bufferedReader().use {
                gson.fromJson(it, type)
            }
        } else {
            emptyList()
        }
    }

    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map {
             if (it.id == id) {
                it.copy(
                    likedByMe = !it.likedByMe,
                    likes = if (!it.likedByMe) it.likes + 1 else it.likes - 1
                )
            } else it
        }
        data.value = posts
        sync()
    }

    override fun shareById(id: Long) {
        posts = posts.map {
            if (it.id == id) {
                it.copy(
                    sharedByMe = !it.sharedByMe,
                    shares = if (!it.sharedByMe) it.shares + 1 else it.shares - 1
                )
            } else it
        }
        data.value = posts
        sync()
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }

    override fun save(post: Post) {
        if (post.id == 0L) {
            posts = listOf(
                post.copy(
                    id = posts.firstOrNull()?.id?.plus(1) ?: 1L,
                    author = "Me",
                    content = post.content,
                    published = "Now",
                    likedByMe = false,
                    likes = 0,
                    sharedByMe = false,
                    shares = 0,
                    views = 1
                )
            ) + posts
            data.value = posts
            return
        }

        posts = posts.map {
            if (it.id != post.id) it else it.copy(content = post.content)
        }
        data.value = posts
    }

}