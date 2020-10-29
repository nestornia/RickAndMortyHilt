package com.example.interview.di

import android.content.Context
import com.example.interview.data.ApiRepo
import com.example.interview.data.RickMortyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ApiModule {
    private const val BASE_URL = "https://rickandmortyapi.com"

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideClient(interceptor: HttpLoggingInterceptor) = OkHttpClient.Builder().apply {
        addInterceptor(interceptor)
    }.build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder =
        Retrofit.Builder().baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())

    @Singleton
    @Provides
    fun provideRickMortyApi(retrofit: Retrofit.Builder): RickMortyApi =
        retrofit.build().create(RickMortyApi::class.java)

    @Singleton
    @Provides
    fun provideApiRepo(
        @ApplicationContext context: Context,
        api: RickMortyApi
    ): ApiRepo = ApiRepo(context, api)
}