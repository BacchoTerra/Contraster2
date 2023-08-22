package com.brunoterra.contraster2.domain.di

import com.brunoterra.contraster2.domain.usecase.ColorHexCalculatorUseCase
import com.brunoterra.contraster2.domain.usecase.HSVChangeUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { HSVChangeUseCase() }
    factory { ColorHexCalculatorUseCase() }
}