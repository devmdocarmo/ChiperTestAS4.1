package com.devmdocarmo.chipertestmovies.provider

import com.devmdocarmo.chipertestmovies.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request

class TMDBClient {
    fun getClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + BuildConfig.ACCESS_TOKEN)
                .addHeader("Content-Type", "application/json;charset=utf-8")
                .build()
            chain.proceed(newRequest)
        }.build()
    }
}
