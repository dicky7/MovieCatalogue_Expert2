package com.example.moviecatalogue.core.domain.useCase

import com.example.moviecatalogue.core.data.source.vo.Resource
import com.example.moviecatalogue.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getMovie(): Flow<Resource<List<Movie>>>
    fun getMovieFav(): Flow<List<Movie>>
    fun setFavMovie(movie: Movie, state: Boolean)
}