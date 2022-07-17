package ru.netology.nmedia.convert

import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.entity.PostEntity

internal fun PostEntity.toModel() = Post(
    id = id,
    author = author,
    content = content,
    published = published,
    likedByMe = likedByMe,
    likes = likes,
    sharedByMe = sharedByMe,
    shares = shares,
    views = views,
    video = video
)

internal fun Post.toEntity() = PostEntity(
    id = id,
    author = author,
    content = content,
    published = published,
    likedByMe = likedByMe,
    likes = likes,
    sharedByMe = sharedByMe,
    shares = shares,
    views = views,
    video = video
)