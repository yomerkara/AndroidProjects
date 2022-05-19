package com.arthas.nasa.module

import com.arthas.nasa.BuildConfig
import com.arthas.nasa.BuildConfig.DEBUG
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    val connectTimeout: Long = 60
    val readTimeout: Long = 60

    fun provideHttpClient(): OkHttpClient {
        val okHttpNetworkInterceptor: Interceptor = getOkHttpNetworkInterceptor()
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
        if (DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
            okHttpClientBuilder.addInterceptor(okHttpNetworkInterceptor)

        }
        okHttpClientBuilder.build()
        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    single { provideHttpClient() }
    single {
        val baseUrl = BuildConfig.BASE_URL
        provideRetrofit(get(), baseUrl)
    }
}

private fun getOkHttpNetworkInterceptor(): Interceptor {
    val API_KEY = "PIcE6uciy8bCJj2aki3EUtQPlL6aluZgmKVJKUqy"
    val HEADER_API_KEY = "api_key"
    return object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val newRequest =
                chain.request().newBuilder().addHeader(HEADER_API_KEY, API_KEY).build()
            return chain.proceed(newRequest)
        }
    }
}
