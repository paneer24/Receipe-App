package com.example.recipeapp.data.repository

import com.example.recipeapp.data.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun searchRecipes(query: String): Flow<Result<List<Recipe>>>
    suspend fun getRecipeById(id: Int): Flow<Result<Recipe>>
}