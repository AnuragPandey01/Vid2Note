package com.example.vidsummary.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.vidsummary.R
import com.example.vidsummary.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import org.aviran.cookiebar2.CookieBar

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!
    private val viewModel : HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        binding.btnGetNotes.setOnClickListener {
            if(binding.etEnterLink.text.toString().isEmpty()){
                CookieBar.build(requireActivity())
                    .setTitle("Error")
                    .setMessage("Please enter a link")
                    .setDuration(5000)
                    .setBackgroundColor(R.color.error_container)
                    .show()
            }else{
                viewModel.getNotes(binding.etEnterLink.text.toString())
            }
        }

        viewModel.state.observe(viewLifecycleOwner){
            when(it){
                is HomeState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnGetNotes.visibility = View.GONE
                }
                is HomeState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToNoteFragment(it.data)
                    )
                }
                is HomeState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnGetNotes.visibility = View.VISIBLE
                    CookieBar.build(requireActivity())
                        .setTitle("Error")
                        .setMessage(it.message)
                        .setDuration(5000)
                        .setBackgroundColor(R.color.error_container)
                        .show()
                }
                is HomeState.Idle -> {}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}