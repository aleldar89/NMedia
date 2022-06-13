package ru.netology.nmedia.viewmodel

import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.post.Post
import ru.netology.nmedia.post.countToString

class PostViewHolder (
    private val binding: CardPostBinding,
    private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            avatar.setImageResource(R.drawable.ic_netology_48)
            author.text = post.author
            published.text = post.published
            content.text = post.content

            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.post_menu)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onInteractionListener.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                onInteractionListener.onEdit(post)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }

            like.setImageResource(
                if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
            )

            like.setOnClickListener {
                onInteractionListener.onLike(post)
            }

            share.setImageResource(
                if (post.sharedByMe) R.drawable.ic_shared_24 else R.drawable.ic_share_24
            )

            share.setOnClickListener {
                onInteractionListener.onShare(post)
            }

            likeCounter.text = countToString(post.liked)
            shareCounter.text = countToString(post.shared)
            viewed.text = countToString(post.viewed)
        }
    }
}