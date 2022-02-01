package com.davidg.candyspacetask.data.di

import com.davidg.candyspacetask.data.common.LoggingInterceptor
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

public val networkingModule = module {

    single { LoggingInterceptor() }

    single {
        MoshiConverterFactory.create(
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        )
    }

    single {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val cache = Cache(androidContext().cacheDir, cacheSize)

        OkHttpClient.Builder().apply {
            addInterceptor(get<LoggingInterceptor>())
            cache(cache)
            connectTimeout(3000, TimeUnit.MILLISECONDS)
            callTimeout(12, TimeUnit.SECONDS)
        }.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.stackexchange.com/2.3/")
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(get<MoshiConverterFactory>())
            .client(get())
            .build()
    }
}
