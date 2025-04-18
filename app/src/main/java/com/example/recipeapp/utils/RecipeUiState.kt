package com.example.recipeapp.utils

import com.example.recipeapp.data.model.Recipe

sealed class RecipeUiState {
    object Loading : RecipeUiState()
    data class Success(val recipes: List<Recipe>) : RecipeUiState()
    object Empty : RecipeUiState()
    data class Error(val message: String) : RecipeUiState()
}