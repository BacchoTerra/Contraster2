package com.brunoterra.contraster2.domain.di

import com.brunoterra.contraster2.domain.usecase.CalculateContrast
import com.brunoterra.contraster2.domain.usecase.CalculateContrastUseCase
import com.brunoterra.contraster2.domain.usecase.ColorHexCalculatorUseCase
import com.brunoterra.contraster2.domain.usecase.HSLChangeUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { HSLChangeUseCase() }
    factory { ColorHexCalculatorUseCase() }
    factory { CalculateContrastUseCase(CalculateContrast()) }
}