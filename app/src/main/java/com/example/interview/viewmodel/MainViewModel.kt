package com.example.interview.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interview.data.ApiRepo
import com.example.interview.model.CharacterResponse
import com.example.interview.util.ApiResponse
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val repo: ApiRepo
) : ViewModel() {
    private val _characters = MutableLiveData<ApiResponse<CharacterResponse>>()
    val characters: LiveData<ApiResponse<CharacterResponse>>
        get() = _characters

    init {
        getCharacters()
    }

    private fun getCharacters() = viewModelScope.launch {
        repo.getCharacters().onEach { _characters.value = it }.launchIn(this)
    }
}