package com.example.moviecatalogue.core.data.source.local

import com.example.moviecatalogue.core.data.source.local.entitiy.MovieEntity
import com.example.moviecatalogue.core.data.source.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {


    fun getDataMovie(): Flow<List<MovieEntity>> {
        return movieDao.getListMovie()
    }

    fun getFavMovie(): Flow<List<MovieEntity>> {
        return movieDao.getFavMovie()
    }

    fun updateFavMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFav = newState
        movieDao.updateFavMovie(movie)
    }

    suspend fun insertMovie(movieList: List<MovieEntity>) {
        return movieDao.insertMovieToFav(movieList)
    }
}