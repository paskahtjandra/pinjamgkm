package com.example.pinjamgkm.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

object RetrofitInstance {

    private val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS) // Set the connection timeout
        .readTimeout(10, TimeUnit.SECONDS) // Set the read timeout
        .writeTimeout(10, TimeUnit.SECONDS) // Set the write timeout
        .addInterceptor(loggingInterceptor)
        .build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
        return try {
            Result.Success(apiCall())
        } catch (e: TimeoutException) {
            Result.Error("Request timeout", e)
        } catch (e: Exception) {
            Result.Error("An error occurred: ${e.message}", e)
        }
    }
}