package com.example.interview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interview.databinding.FragmentCharacterListBinding
import com.example.interview.model.Character
import com.example.interview.model.CharacterResponse
import com.example.interview.util.ApiResponse
import com.example.interview.util.extensions.show
import com.example.interview.viewmodel.MainViewModel

class CharacterListFragment : Fragment() {
    private lateinit var binding: FragmentCharacterListBinding
    private val vm by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCharacterListBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() = with(binding.rvList) {
        layoutManager = LinearLayoutManager(context)
        setHasFixedSize(true)
        adapter = CharacterAdapter(::characterSelected)
    }

    private fun setupObservers() = with(vm) {
        characters.observe(viewLifecycleOwner, ::parseCharacterResponse)
    }

    private fun parseCharacterResponse(response: ApiResponse<CharacterResponse>) = when (response) {
        is ApiResponse.Success -> binding.progress.show(false).also {
            (binding.rvList.adapter as CharacterAdapter).loadCharacters(response.data.results)
        }
        is ApiResponse.Error -> binding.progress.show(false)
        is ApiResponse.Loading -> binding.progress.show()
    }

    private fun characterSelected(character: Character) = with(CharacterListFragmentDirections) {
        findNavController().navigate(goToCharacterDetail(character))
    }
}