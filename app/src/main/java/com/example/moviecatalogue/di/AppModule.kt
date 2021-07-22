package com.example.moviecatalogue.di

import com.example.moviecatalogue.core.domain.useCase.MovieInteractor
import com.example.moviecatalogue.core.domain.useCase.MovieUseCase
import com.example.moviecatalogue.detail.DetailMovieViewModel
import com.example.moviecatalogue.home.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
}