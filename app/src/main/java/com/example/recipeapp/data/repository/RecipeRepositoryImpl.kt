package com.example.recipeapp.data.repository

import com.example.recipeapp.data.api.RecipeService
import com.example.recipeapp.data.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RecipeRepositoryImpl(private val recipeService: RecipeService) : RecipeRepository {

    override suspend fun searchRecipes(query: String): Flow<Result<List<Recipe>>> = flow {
        try {
            val recipes = recipeService.searchRecipes(query)
            emit(Result.success(recipes))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getRecipeById(id: Int): Flow<Result<Recipe>> = flow {
        try {
            val recipe = recipeService.getRecipeById(id)
            emit(Result.success(recipe))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)
}