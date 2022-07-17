package ru.netology.nmedia.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.dto.Post

@Entity(tableName = "posts")
class PostEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "published")
    val published: String,

    @ColumnInfo(name = "likedByMe")
    val likedByMe: Boolean,

    @ColumnInfo(name = "likes")
    val likes: Int,

    @ColumnInfo(name = "sharedByMe")
    val sharedByMe: Boolean,

    @ColumnInfo(name = "shares")
    val shares: Int,

    @ColumnInfo(name = "views")
    val views: Int,

    @ColumnInfo(name = "video")
    val video: String? = null,
)