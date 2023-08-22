package com.brunoterra.contraster2.presentation.di

import com.brunoterra.contraster2.presentation.contrast.ContrastViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { ContrastViewModel(get(), get()) }
}