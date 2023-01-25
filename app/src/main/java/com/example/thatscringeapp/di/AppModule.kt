package com.example.thatscringeapp.di

import com.example.thatscringeapp.data.remote.OpenAiService
import com.example.thatscringeapp.data.repository.CringifyRepositoryImpl
import com.example.thatscringeapp.domain.repository.CringifyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOpenAi(): OpenAiService {
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl("https://api.openai.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(OpenAiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCringifyRepository(api: OpenAiService): CringifyRepository {
        return CringifyRepositoryImpl(api = api)
    }
}