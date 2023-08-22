package com.brunoterra.contraster2.domain.utils

import com.brunoterra.contraster2.domain.usecase.HSVChangeUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { HSVChangeUseCase() }
}