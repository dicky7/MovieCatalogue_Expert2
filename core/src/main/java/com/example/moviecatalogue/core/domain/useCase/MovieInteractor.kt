package com.example.moviecatalogue.core.domain.useCase

import com.example.moviecatalogue.core.domain.model.Movie
import com.example.moviecatalogue.core.domain.repository.MovieRepo

class MovieInteractor(private val movieRepository: MovieRepo) : MovieUseCase {
    override fun getMovie() = movieRepository.getMovie()
    override fun getMovieFav() = movieRepository.getMovieFav()
    override fun setFavMovie(movie: Movie, state: Boolean) =
        movieRepository.setMovieFav(movie, state)
}