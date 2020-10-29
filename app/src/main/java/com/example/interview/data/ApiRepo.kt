package com.example.interview.data

import android.accounts.NetworkErrorException
import android.content.Context
import com.example.interview.util.extensions.isConnected
import com.example.interview.util.ApiResponse
import kotlinx.coroutines.flow.flow

class ApiRepo constructor(
    private val context: Context,
    private val api: RickMortyApi
) {

    suspend fun getCharacters() = flow {
        emit(ApiResponse.Loading)
        if (context.isConnected) {
            try {
                val response = api.getAllCharacters()
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e))
            }
        } else emit(ApiResponse.Error(NetworkErrorException("No Connection")))
    }
}