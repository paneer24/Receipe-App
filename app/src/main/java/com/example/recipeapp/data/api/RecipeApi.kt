package com.example.recipeapp.data.api

import com.example.recipeapp.data.model.RecipeDetailResponse
import com.example.recipeapp.data.model.RecipeSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {

    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("query") query: String,
        @Query("number") number: Int=10
    ): RecipeSearchResponse

    @GET("recipes/{id}/information")
    suspend fun getRecipeById(
        @Path("id") id: Int,
        @Query("includeNutrition") includeNutrition: Boolean = false,

    ): RecipeDetailResponse

}