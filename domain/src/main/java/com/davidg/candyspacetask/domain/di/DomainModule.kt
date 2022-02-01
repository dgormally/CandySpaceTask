package com.davidg.candyspacetask.domain.di

import com.davidg.candyspacetask.domain.usecase.GetUsersUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetUsersUseCase(get()) }
}
