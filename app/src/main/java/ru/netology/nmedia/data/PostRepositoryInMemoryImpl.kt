package ru.netology.nmedia.data

import ru.netology.nmedia.post.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PostRepositoryInMemoryImpl: PostRepository  {

    private var post = Post(
        id = 1,
        author = "Нетология. Университет интернет-профессий будущего",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
        published = "21 мая в 18:36",
        likedByMe = false,
        liked = 0,
        shared = 0,
        viewed = 1
    )

    private var data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data

    override fun like() {
        post = if (!post.likedByMe)
            post.copy(likedByMe = true, liked = post.liked + 1)
        else
            post.copy(likedByMe = false, liked = post.liked - 1)
        data.value = post
    }

    override fun share() {
        post = post.copy(shared = post.shared + 1)
        data.value = post
    }

}