package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.post.Post
import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.viewmodel.OnInteractionListener
import ru.netology.nmedia.viewmodel.PostViewModel
import ru.netology.nmedia.viewmodel.PostsAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter(
            object : OnInteractionListener {
                override fun onLike(post: Post) {
                    viewModel.likeById(post.id)
                }

                override fun onShare(post: Post) {
                    viewModel.shareById(post.id)
                }

                override fun onEdit(post: Post) {
                    binding.apply {
                        //при выборе "редактировать" всплывает плашка редактирования
                        editGroup.visibility = View.VISIBLE
                        editedMessage.text = post.content

                        //передает пост в edited
                        viewModel.edit(post)
                    }
                }

                override fun onRemove(post: Post) {
                    viewModel.removeById(post.id)
                }
            }
        )

        binding.save.setOnClickListener {
            with(binding.content) {
                if (text.isNullOrBlank()) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.error_empty_content),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                //отправляет введенный текст в редактируемый пост или формирует новый пост
                viewModel.changeContent(text.toString().trim())
                viewModel.save()

                //при нажатии на save плашка редактирования исчезает
                binding.editGroup.visibility = View.GONE

                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }

        binding.list.adapter = adapter

        viewModel.edited.observe(this) { post ->
            if (post.id == 0L) {
                return@observe
            }

            with(binding.content) {
                /* при нажатии на "отмену" исчезает плашка редактирования, вводимый текст,
                убирается клавиатура, вычищается контейнер edited в PostViewModel*/
                binding.cancelEdit.setOnClickListener {
                    binding.editGroup.visibility = View.GONE
                    binding.content.text.clear()
                    viewModel.clearEditedData()
                    AndroidUtils.hideKeyboard(this)
                }
                requestFocus()
                //заполняет вводимый текст содержимым поста
                setText(post.content)
            }
        }

        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
    }
}