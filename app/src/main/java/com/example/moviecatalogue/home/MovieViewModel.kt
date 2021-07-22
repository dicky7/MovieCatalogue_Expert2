package com.example.moviecatalogue.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.moviecatalogue.core.domain.useCase.MovieUseCase

class MovieViewModel(mUseCase: MovieUseCase) : ViewModel() {
    val movies = mUseCase.getMovie().asLiveData()
}