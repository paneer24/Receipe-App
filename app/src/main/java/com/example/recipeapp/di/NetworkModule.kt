package com.example.recipeapp.di


import com.example.recipeapp.data.api.RecipeApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.example.recipeapp.BuildConfig
import com.example.recipeapp.data.api.RecipeService

val networkModule= module {
    single {    "https://api.spoonacular.com/"  }

    single {
        provideApiKeyInterceptor()
    }
    single {
        provideOkHttpClient(get())
    }
    single {
        provideRetrofit(get(), get())
    }
    single {
        get<Retrofit>().create(RecipeApi::class.java)
    }
    single {
        RecipeService(get())
    }
}
private fun provideApiKeyInterceptor() = Interceptor { chain ->
    val original = chain.request()
    val originalUrl = original.url

    val url = originalUrl.newBuilder()
        .addQueryParameter("apiKey", BuildConfig.API_KEY)
        .build()

    val request = original.newBuilder()
        .url(url)
        .build()

    chain.proceed(request)
}
private fun provideOkHttpClient(apiKeyInterceptor: Interceptor) = OkHttpClient.Builder()
    .addInterceptor(apiKeyInterceptor)
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    })
    .connectTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .build()

private fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient) = Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
