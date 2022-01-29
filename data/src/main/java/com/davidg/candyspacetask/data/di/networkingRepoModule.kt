package com.davidg.candyspacetask.data.di

import com.davidg.candyspacetask.data.api.StackExchangeApi
import com.davidg.candyspacetask.data.repos.NetworkRepoImpl
import com.davidg.candyspacetask.domain.repos.NetworkRepo
import org.koin.dsl.module
import retrofit2.Retrofit

val networkingRepoModule = module {

    single { get<Retrofit>().create(StackExchangeApi::class.java) }
    single<NetworkRepo> { NetworkRepoImpl(get()) }

}
