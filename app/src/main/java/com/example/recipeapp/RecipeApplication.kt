package com.example.recipeapp

import android.app.Application
import com.example.recipeapp.di.appModule
import com.example.recipeapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class RecipeApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@RecipeApplication)
            modules( appModule,networkModule)
        }
    }
}