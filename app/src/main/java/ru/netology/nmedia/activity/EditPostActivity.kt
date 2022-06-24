package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.EditPostBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class EditPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = EditPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postContent = intent.extras?.getString("content")

        with(binding) {
            editedText.setText(postContent)

            buttonEdit.setOnClickListener {
                if (editedText.text.isNullOrBlank()) {
                    Toast.makeText(
                        this@EditPostActivity,
                        getString(R.string.error_empty_content),
                        Toast.LENGTH_SHORT
                    ).show()
                    setResult(Activity.RESULT_CANCELED)
                    return@setOnClickListener
                } else {
                    setResult(
                        Activity.RESULT_OK,
                        Intent().putExtra(Intent.EXTRA_TEXT, editedText.text.toString())
                    )
                }
                finish()
            }
        }

    }
}