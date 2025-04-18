package com.example.recipeapp.data.api

import com.example.recipeapp.data.model.Ingredient
import com.example.recipeapp.data.model.Recipe
import com.example.recipeapp.data.model.RecipeDetailResponse

class RecipeService(private val recipeApi: RecipeApi) {


    suspend fun searchRecipes(query: String): List<Recipe> {
        val response = recipeApi.searchRecipes(query = query)
        val recipeIds = response.results.map { it.id }

        return recipeIds.map { id ->
            getRecipeById(id)
        }
    }

    suspend fun getRecipeById(id: Int): Recipe {
        val response = recipeApi.getRecipeById(id = id)
        return mapResponseToRecipe(response)
    }

    private fun mapResponseToRecipe(response: RecipeDetailResponse): Recipe {
        return Recipe(
            id = response.id,
            title = response.title,
            image = response.image,
            readyInMinutes = response.readyInMinutes,
            servings = response.servings,
            summary = response.summary,
            instructions = response.instructions,
            ingredients = response.extendedIngredients.map {
                Ingredient(
                    id = it.id,
                    name = it.name,
                    amount = it.amount,
                    unit = it.unit
                )
            }
        )
    }
}