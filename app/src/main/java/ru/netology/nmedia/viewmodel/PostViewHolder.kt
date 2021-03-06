package ru.netology.nmedia.viewmodel

import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.FragmentCardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.countToString
import ru.netology.nmedia.util.setAllOnClickListener

class PostViewHolder (
    private val binding: FragmentCardPostBinding,
    private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.apply {
            avatar.setImageResource(R.drawable.ic_netology_48)
            author.text = post.author
            published.text = post.published
            content.text = post.content

            menu.setOnClickListener {
                menu.isChecked = true

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

                    setOnDismissListener {
                        binding.menu.isChecked = false
                    }

                }.show()
            }

            if (post.video != null)
                videoGroup.visibility = View.VISIBLE
            else
                videoGroup.visibility = View.GONE

            videoGroup.setAllOnClickListener {
                onInteractionListener.onPlay(post)
            }

            content.setOnClickListener {
                onInteractionListener.onChoose(post)
            }

            like.setOnClickListener {
                onInteractionListener.onLike(post)
            }
            like.isChecked = post.likedByMe
            like.text = countToString(post.likes)


            share.setOnClickListener {
                onInteractionListener.onShare(post)
            }
            share.isChecked = post.sharedByMe
            share.text = countToString(post.shares)

            viewed.text = countToString(post.views)

        }
    }
}