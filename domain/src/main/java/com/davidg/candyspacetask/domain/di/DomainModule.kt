package com.davidg.candyspacetask.domain.di

import com.davidg.candyspacetask.domain.usecase.SearchByNameUseCase
import org.koin.dsl.module

val domainModule = module {
    single { SearchByNameUseCase(get()) }
}
