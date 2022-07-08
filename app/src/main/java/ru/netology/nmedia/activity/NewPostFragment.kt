package ru.netology.nmedia.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.databinding.FragmentNewPostBinding
import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.util.StringArg
import ru.netology.nmedia.viewmodel.PostViewModel

class NewPostFragment : Fragment() {

    companion object {
        var Bundle.textArg: String? by StringArg
    }

    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNewPostBinding.inflate(
            inflater,
            container,
            false
        )

        arguments?.textArg
            ?.let(binding.edit::setText)

//        binding.edit.setText(arguments?.getString("content"))

//        binding.edit.setText(arguments?.get("content").toString())

        binding.edit.requestFocus()


        binding.ok.setOnClickListener {
            viewModel.changeContent(binding.edit.text.toString())
            viewModel.save()
            AndroidUtils.hideKeyboard(requireView())
            findNavController().navigateUp()
        }

        binding.cancel.setOnClickListener {
            viewModel.clearEditedData()
            findNavController().navigateUp()
        }

        return binding.root
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val binding = FragmentNewPostBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        with(binding) {
//
//            edit.setText(intent.extras?.getString(Intent.EXTRA_TEXT))
//
//            ok.setOnClickListener {
//                if (tempText.text.isNullOrBlank()) {
//                    Toast.makeText(
//                        this@NewPostFragment,
//                        getString(R.string.error_empty_content),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    setResult(Activity.RESULT_CANCELED)
//                    return@setOnClickListener
//                } else {
//                    setResult(
//                        Activity.RESULT_OK,
//                        Intent().putExtra(Intent.EXTRA_TEXT, tempText.text.toString())
//                    )
//                }
//                finish()
//            }
//        }
//
//        binding.cancelEdit.setOnClickListener {
//            viewModel.clearEditedData()
//            finish()
//        }
//
//    }
}