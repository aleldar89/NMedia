package ru.netology.nmedia.viewmodel

import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.post.Post
import ru.netology.nmedia.post.countToString

class PostViewHolder (
    private val binding: CardPostBinding,
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            avatar.setImageResource(R.drawable.ic_netology_48)
            author.text = post.author
            published.text = post.published
            content.text = post.content
            like.setImageResource(
                if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
            )
            like.setOnClickListener {
                onLikeListener(post)
            }
            share.setImageResource(
                if (post.sharedByMe) R.drawable.ic_shared_24 else R.drawable.ic_share_24
            )
            share.setOnClickListener {
                onShareListener(post)
            }
            likeCounter.text = countToString(post.liked)
            shareCounter.text = countToString(post.shared)
            viewed.text = countToString(post.viewed)
        }
    }
}