package com.example.interview.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterResponse(
    val info: Info? = null,
    val results: List<Character> = listOf()
)