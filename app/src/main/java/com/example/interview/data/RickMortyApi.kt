package com.example.interview.data

import com.example.interview.model.CharacterResponse
import retrofit2.http.GET

interface RickMortyApi {

    @GET("/api/character/")
    suspend fun getAllCharacters() : CharacterResponse
}