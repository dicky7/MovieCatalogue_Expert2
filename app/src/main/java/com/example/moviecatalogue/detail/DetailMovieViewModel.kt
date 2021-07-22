package com.example.moviecatalogue.detail

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.core.domain.model.Movie
import com.example.moviecatalogue.core.domain.useCase.MovieUseCase

class DetailMovieViewModel(private val mUseCase: MovieUseCase) : ViewModel() {
    fun setMovieFav(movie: Movie, newState: Boolean) = mUseCase.setFavMovie(movie, newState)
}