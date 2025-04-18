package com.example.recipeapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.model.Recipe
import com.example.recipeapp.data.repository.RecipeRepository
import com.example.recipeapp.utils.RecipeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RecipeViewModel(
    private val repository: RecipeRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<RecipeUiState>(RecipeUiState.Loading)
    val uiState: StateFlow<RecipeUiState> = _uiState.asStateFlow()

    private val _selectedRecipe = MutableStateFlow<Recipe?>(null)
    val selectedRecipe: StateFlow<Recipe?> = _selectedRecipe.asStateFlow()

    init {
        searchRecipes("pasta")
    }

    fun searchRecipes(query: String) {
        viewModelScope.launch {
            _uiState.value = RecipeUiState.Loading
            repository.searchRecipes(query).collectLatest { result ->
                result.fold(
                    onSuccess = { recipe ->
                        _uiState.value =
                            if (recipe.isEmpty()) {
                                RecipeUiState.Empty
                            } else {
                                RecipeUiState.Success(recipe)
                            }
                    },
                    onFailure = {
                        _uiState.value = RecipeUiState.Error("Error fetching recipes")
                    },
                )
            }
        }
    }

    fun getRecipeById(id: Int) {
        viewModelScope.launch {
            repository.getRecipeById(id).collectLatest { result ->
                result.fold(
                    onSuccess = { recipe ->
                        _selectedRecipe.value = recipe
                    },
                    onFailure = {
                        _uiState.value = RecipeUiState.Error("Error fetching recipe")

                    })
            }
        }
    }
    fun selectRecipe(recipe: Recipe) {
        _selectedRecipe.value = recipe
    }

    fun clearSelectedRecipe() {
        _selectedRecipe.value = null
    }
}