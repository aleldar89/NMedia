package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.TempPostBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class TempPostActivity : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = TempPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {

            tempText.setText(intent.extras?.getString(Intent.EXTRA_TEXT))

            buttonOk.setOnClickListener {
                if (tempText.text.isNullOrBlank()) {
                    Toast.makeText(
                        this@TempPostActivity,
                        getString(R.string.error_empty_content),
                        Toast.LENGTH_SHORT
                    ).show()
                    setResult(Activity.RESULT_CANCELED)
                    return@setOnClickListener
                } else {
                    setResult(
                        Activity.RESULT_OK,
                        Intent().putExtra(Intent.EXTRA_TEXT, tempText.text.toString())
                    )
                }
                finish()
            }
        }

        binding.cancelEdit.setOnClickListener {
            viewModel.clearEditedData()
            finish()
        }

    }
}