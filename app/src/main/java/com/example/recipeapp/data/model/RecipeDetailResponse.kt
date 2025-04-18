package com.example.recipeapp.data.model

data class RecipeDetailResponse(
    val id: Int,
    val title: String,
    val image: String?,
    val readyInMinutes: Int,
    val servings: Int,
    val summary: String,
    val instructions: String?,
    val extendedIngredients: List<IngredientResponse>
)