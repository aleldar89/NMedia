package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.NewPostBinding

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = NewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            buttonOk.setOnClickListener {
                if (newText.text.isNullOrBlank()) {
                    Toast.makeText(
                        this@NewPostActivity,
                        getString(R.string.error_empty_content),
                        Toast.LENGTH_SHORT
                    ).show()
                    setResult(Activity.RESULT_CANCELED)
                    return@setOnClickListener
                } else {
                    setResult(
                        Activity.RESULT_OK,
                        Intent().putExtra(Intent.EXTRA_TEXT, newText.text.toString())
                    )
                }
                finish()
            }
        }

    }
}