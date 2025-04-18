package com.example.recipeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.ui.screen.RecipeDetailScreen
import com.example.recipeapp.ui.screen.RecipeListScreen
import com.example.recipeapp.ui.viewmodel.RecipeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecipeApp(
    navController: NavHostController = rememberNavController(),
    viewModel: RecipeViewModel = koinViewModel(),
) {
    val selectedRecipe by viewModel.selectedRecipe.collectAsState()

    NavHost(
        navController = navController,
        startDestination = "recipeList"
    ) {
        composable("recipeList") {
            RecipeListScreen(
                onRecipeClick = { recipe ->
                    viewModel.selectRecipe(recipe)
                    navController.navigate("recipeDetail")
                }
            )
        }
        composable("recipeDetail") {
            selectedRecipe?.let { recipe ->
                RecipeDetailScreen(
                    recipe = recipe,
                    onBackClick = {
                        navController.popBackStack()
                        viewModel.clearSelectedRecipe()
                    }
                )
            }
        }


    }
}