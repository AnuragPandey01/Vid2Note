package com.example.vidsummary.ui.notes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.vidsummary.R
import com.example.vidsummary.databinding.FragmentNoteBinding
import dagger.hilt.android.AndroidEntryPoint
import org.aviran.cookiebar2.CookieBar

@AndroidEntryPoint
class NoteFragment : Fragment(R.layout.fragment_note) {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NoteViewModel by viewModels()
    private val args by navArgs<NoteFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNoteBinding.bind(view)

        binding.apply {
            noteTitle.text = args.notesResponse.title
            noteContent.text = args.notesResponse.content_block_1 + "\n" + args.notesResponse.content_block_2 + "\n" + args.notesResponse.content_block_3
            Glide.with(requireContext())
                .load(args.notesResponse.thumbnail_url)
                .centerCrop()
                .into(noteImage)
            goBack.setOnClickListener {
                findNavController().popBackStack()
            }

            getQuizButton.setOnClickListener {
                viewModel.getQuiz(args.notesResponse)
            }
        }

        viewModel.state.observe(viewLifecycleOwner){
            when(it){
                is NoteViewModelState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.getQuizButton.visibility = View.GONE
                }
                is NoteViewModelState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val action = NoteFragmentDirections.actionNoteFragmentToQuizFragment(
                        title = args.notesResponse.title ?: "No title",
                        quizResponse = it.data
                    )
                    findNavController().navigate(action)
                }
                is NoteViewModelState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.getQuizButton.visibility = View.VISIBLE
                    CookieBar.build(requireActivity())
                        .setTitle("Error")
                        .setMessage(it.message)
                        .setBackgroundColor(R.color.error_container)
                        .show()
                }
                else -> Unit

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}