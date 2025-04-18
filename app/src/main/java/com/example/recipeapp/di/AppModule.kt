package com.example.recipeapp.di

import com.example.recipeapp.data.repository.RecipeRepository
import com.example.recipeapp.data.repository.RecipeRepositoryImpl
import com.example.recipeapp.ui.viewmodel.RecipeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule= module {
    single<RecipeRepository> { RecipeRepositoryImpl(get()) }
    viewModel { RecipeViewModel(get()) }

}