package com.brunoterra.contraster2.domain.di

import com.brunoterra.contraster2.domain.usecase.contrast.CalculateContrastImpl
import com.brunoterra.contraster2.domain.usecase.contrast.CalculateContrastUseCase
import com.brunoterra.contraster2.domain.usecase.contrast.ColorHexCalculatorUseCase
import com.brunoterra.contraster2.domain.usecase.contrast.HSLChangeUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { HSLChangeUseCase() }
    factory { ColorHexCalculatorUseCase() }
    factory { CalculateContrastUseCase(CalculateContrastImpl()) }
}
