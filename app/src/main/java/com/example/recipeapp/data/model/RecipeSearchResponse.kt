package com.example.recipeapp.data.model

data class RecipeSearchResponse(
    val results: List<RecipePreview>,
    val offset: Int,
    val number: Int,
    val totalResults: Int
)
