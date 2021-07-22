package com.example.moviecatalogue.core.domain.repository

import com.example.moviecatalogue.core.data.source.vo.Resource
import com.example.moviecatalogue.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepo {
    fun getMovie(): Flow<Resource<List<Movie>>>
    fun getMovieFav(): Flow<List<Movie>>
    fun setMovieFav(movie: Movie, state: Boolean)
}