package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36"
        )
        with(binding){
            author.text = post.author
            published.text = post.published
            content.text = post.content
            if (post.likedByMe) {
                like.setImageResource(R.drawable.ic_liked_24)
            }
            likeCounter.text = counterString(post.liked)
            shareCounter.text = counterString(post.shared)
            viewed.text = shareCounter.text
            avatar.setImageResource(R.drawable.ic_netology_48)

            like.setOnClickListener {
                post.likedByMe = !post.likedByMe
                if (post.likedByMe) {
                    like.setImageResource(R.drawable.ic_liked_24)
                    post.liked++
                }
                else {
                    like.setImageResource(R.drawable.ic_like_24)
                    post.liked--
                }
                likeCounter.text = counterString(post.liked)
            }

            share.setOnClickListener {
                post.shared++
                shareCounter.text = counterString(post.shared)
                viewed.text = shareCounter.text
            }

        }
    }
}